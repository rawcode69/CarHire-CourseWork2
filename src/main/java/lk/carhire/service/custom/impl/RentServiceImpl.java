package lk.carhire.service.custom.impl;


import lk.carhire.dao.DaoFactory;
import lk.carhire.dao.custom.CarDao;
import lk.carhire.dao.custom.CustomerDao;
import lk.carhire.dao.custom.RentDao;
import lk.carhire.dto.RentDto;
import lk.carhire.entity.CarEntity;
import lk.carhire.entity.CustomerEntity;
import lk.carhire.entity.RentEntity;
import lk.carhire.service.custom.RentService;
import lk.carhire.util.SessionFactoryConfiguration;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class RentServiceImpl implements RentService {

    Session session = SessionFactoryConfiguration.getInstance().getSession();
    RentDao rentDao = (RentDao) DaoFactory.getInstance().getDao(DaoFactory.DaoType.RENT);
    CustomerDao customerDao = (CustomerDao) DaoFactory.getInstance().getDao(DaoFactory.DaoType.CUSTOMER);
    CarDao carDao = (CarDao) DaoFactory.getInstance().getDao(DaoFactory.DaoType.CAR);

    @Override
    public String placeRent(RentDto rentDto) throws Exception {

        CustomerEntity customerEntity = customerDao.get(rentDto.getCustomerId(), session);
        CarEntity carEntity = carDao.getCarByCarNumber(rentDto.getCarNumber(), session);

        Transaction transaction = session.beginTransaction();


        RentEntity rentEntity = new RentEntity();

        rentEntity.setDate(rentDto.getRentDate());
        rentEntity.setStartDate(rentDto.getStartDate());
        rentEntity.setEndDate(rentDto.getStartDate());
        rentEntity.setRate(rentDto.getRate());
        rentEntity.setTotal(rentDto.getTotal());
        rentEntity.setDeposit(rentDto.getDepositAmount());
        rentEntity.setAdvancedPayment(rentDto.getAdvancedPayment());


        Integer rentId = rentDao.add(rentEntity, session);

        if (rentId != null) {

            boolean isCustomerUpdated = true;

            customerEntity.setToReturn(rentDto.getEndDate());
            customerDao.update(customerEntity);

            CustomerEntity customerEntity1 = customerDao.get(rentDto.getCustomerId(), session);

            if (customerEntity1.getToReturn() == null) {
                isCustomerUpdated = false;
            }

            if (isCustomerUpdated) {
                transaction.commit();

                boolean isCarUpdated = true;

                carEntity.setIsRentable(false);
                carDao.update(carEntity);

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
}
