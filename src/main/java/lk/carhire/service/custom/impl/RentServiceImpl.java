package lk.carhire.service.custom.impl;


import lk.carhire.dao.DaoFactory;
import lk.carhire.dao.custom.CarDao;
import lk.carhire.dao.custom.CustomerDao;
import lk.carhire.dao.custom.RentDao;
import lk.carhire.dto.OngoingRentDto;
import lk.carhire.dto.RentDto;
import lk.carhire.entity.CarEntity;
import lk.carhire.entity.CustomerEntity;
import lk.carhire.entity.RentEntity;
import lk.carhire.service.custom.RentService;
import lk.carhire.util.SessionFactoryConfiguration;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;

public class RentServiceImpl implements RentService {

    Session session = SessionFactoryConfiguration.getInstance().getSession();
    RentDao rentDao = (RentDao) DaoFactory.getInstance().getDao(DaoFactory.DaoType.RENT);
    CustomerDao customerDao = (CustomerDao) DaoFactory.getInstance().getDao(DaoFactory.DaoType.CUSTOMER);
    CarDao carDao = (CarDao) DaoFactory.getInstance().getDao(DaoFactory.DaoType.CAR);

    @Override
    public String placeRent(RentDto rentDto) throws Exception {

        // This is the transaction for a place new rent

        CustomerEntity customerEntity = customerDao.get(rentDto.getCustomerId(), session);
        CarEntity carEntity = carDao.getCarByCarNumber(rentDto.getCarNumber(), session);

        RentEntity rentEntity = new RentEntity();

        rentEntity.setDate(rentDto.getRentDate());
        rentEntity.setStartDate(rentDto.getStartDate());
        rentEntity.setEndDate(rentDto.getEndDate());
        rentEntity.setRate(rentDto.getRate());
        rentEntity.setTotal(rentDto.getTotal());
        rentEntity.setDeposit(rentDto.getDepositAmount());
        rentEntity.setAdvancedPayment(rentDto.getAdvancePayment());
        rentEntity.setCarEntity(carEntity);
        rentEntity.setCustomerEntity(customerEntity);
        rentEntity.setIsActive(rentDto.getIsActive());

        Transaction transaction = session.beginTransaction();
        Integer rentId = rentDao.add(rentEntity, session);

        if (rentId != null) {

            boolean isCustomerUpdated = true;

            customerEntity.setToReturn(rentDto.getEndDate());

            customerDao.update(customerEntity, session);

            CustomerEntity customerEntity1 = customerDao.get(rentDto.getCustomerId(), session);

            if (customerEntity1.getToReturn() == null) {
                isCustomerUpdated = false;
            }

            if (isCustomerUpdated) {
                //  transaction.commit();

                boolean isCarUpdated = true;

                carEntity.setIsRentable(false);
                carDao.update(carEntity, session);

                CarEntity carEntity1 = carDao.getCarByCarNumber(rentDto.getCarNumber(), session);

                if (carEntity1.getIsRentable()) {
                    isCarUpdated = false;
                }

                if (isCarUpdated) {
                    transaction.commit();
                    return "Rent Placed Successfully";
                } else {
                    transaction.rollback();
                    return "Car Update Error";
                }

            } else {
                transaction.rollback();
                return "Customer Update Error";
            }
        } else {
            transaction.rollback();
            return "Rent Save Error";
        }

    }

    @Override
    public List<RentDto> getAllActiveRents() throws Exception {
        List<RentEntity> rentEntities = rentDao.getAllActive();
        List<RentDto> rentDtos = new ArrayList<>();

        for (RentEntity rentEntity : rentEntities) {
            RentDto rentDto = new RentDto();

            rentDto.setId(rentEntity.getId());
            rentDto.setRentDate(rentEntity.getDate());
            rentDto.setStartDate(rentEntity.getStartDate());
            rentDto.setEndDate(rentEntity.getEndDate());
            rentDto.setAdvancePayment(rentEntity.getAdvancedPayment());
            rentDto.setDepositAmount(rentDto.getDepositAmount());
            rentDto.setCustomerId(rentEntity.getCustomerEntity().getId());
            rentDto.setCarCategory(rentEntity.getCarEntity().getCategoryEntity().getId());
            rentDto.setCarNumber(rentEntity.getCarEntity().getNumber());
            rentDto.setRate(rentEntity.getRate());
            rentDto.setTotal(rentEntity.getTotal());


            rentDtos.add(rentDto);
        }
        return rentDtos;
    }



    @Override
    public RentDto getRent(Integer rentId) throws Exception {
        RentEntity rentEntity = rentDao.get(rentId);

        RentDto rentDto = new RentDto();

        rentDto.setCustomerId(rentEntity.getCustomerEntity().getId());

        return rentDto;
    }

    @Override
    public String closeRent(OngoingRentDto ongoingRentDto) throws Exception {

        Transaction transaction = session.beginTransaction();

        RentEntity rentEntity = rentDao.get(ongoingRentDto.getRentId(),session);
        CustomerEntity customerEntity = customerDao.get(ongoingRentDto.getCustId(),session);
        CarEntity carEntity = carDao.get(ongoingRentDto.getCarId(),session);



        Double total = ongoingRentDto.getBalanceToPay() + rentEntity.getAdvancedPayment();
        rentEntity.setIsActive(false);
        rentEntity.setTotal(total);

        rentDao.update(rentEntity, session);

        RentEntity rentEntity1 = rentDao.get(ongoingRentDto.getRentId(), session);

        if (!rentEntity1.getIsActive()) {

            boolean isCustomerUpdated = true;

            customerEntity.setToReturn(null);

            customerDao.update(customerEntity, session);

            CustomerEntity customerEntity1 = customerDao.get(ongoingRentDto.getCustId(), session);

            if (customerEntity1.getToReturn() != null) {
                isCustomerUpdated = false;
            }

            if (isCustomerUpdated) {

                boolean isCarUpdated = true;

                carEntity.setIsRentable(true);

                carDao.update(carEntity, session);

                CarEntity carEntity1 = carDao.get(ongoingRentDto.getCarId(),session);

                if (!carEntity1.getIsRentable()) {
                    isCarUpdated = false;
                }

                if (isCarUpdated) {

                    transaction.commit();
                    return "Rent Closed SuccessFully";

                } else {
                    transaction.rollback();
                    return "Car Update Error";
                }

            } else {
                transaction.rollback();
                return "Customer Update Error";
            }

        } else {
            transaction.rollback();
            return "Rent Update Error";
        }

    }
}
