package lk.carhire.service.custom.impl;

import lk.carhire.dao.DaoFactory;
import lk.carhire.dao.custom.CustomerDao;
import lk.carhire.dto.CustomerDto;
import lk.carhire.entity.CustomerEntity;
import lk.carhire.entity.embedded.CustomerAddress;
import lk.carhire.entity.embedded.CustomerFullName;
import lk.carhire.service.custom.CustomerService;

import java.util.ArrayList;
import java.util.List;

public class CustomerServiceImpl implements CustomerService {

    CustomerDao customerDao = (CustomerDao) DaoFactory.getInstance().getDao(DaoFactory.DaoType.CUSTOMER);
    @Override
    public Integer saveCustomer(CustomerDto customerDto) throws Exception {
        CustomerEntity customerEntity = new CustomerEntity();

        customerEntity.setUserName(customerDto.getUserName());
        customerEntity.setEmail(customerDto.getEmail());
        CustomerFullName customerFullName = new CustomerFullName(customerDto.getFirstName(),customerDto.getLastName());
        customerEntity.setFullName(customerFullName);
        CustomerAddress address = new CustomerAddress(customerDto.getStreet(),customerDto.getCity(),customerDto.getDistrict());
        customerEntity.setAddress(address);
        customerEntity.setPostalCode(customerDto.getPostalCode());
        List <String> mobiles = new ArrayList<>();
        mobiles.add(customerDto.getMobile());
        customerEntity.setMobiles(mobiles);

        return customerDao.add(customerEntity);

    }

    @Override
    public CustomerDto getCustomer(Integer id) throws Exception {

        CustomerEntity customerEntity = customerDao.get(id);
        CustomerDto customerDto = new CustomerDto();

        customerDto.setUserName(customerEntity.getUserName());
        customerDto.setEmail(customerEntity.getEmail());
        customerDto.setFirstName(customerEntity.getFullName().getFirstName());
        customerDto.setLastName(customerEntity.getFullName().getLastName());
        customerDto.setStreet(customerEntity.getAddress().getStreet());
        customerDto.setCity(customerEntity.getAddress().getCity());
        customerDto.setDistrict(customerEntity.getAddress().getDistrict());
        customerDto.setPostalCode(customerEntity.getPostalCode());
        customerDto.setMobile(customerEntity.getMobiles().toString());
        customerDto.setToReturn(customerEntity.getToReturn());
        return  customerDto;
    }

    @Override
    public void updateCustomer(CustomerDto customerDto) throws Exception {

        CustomerEntity customerEntity = new CustomerEntity();

        customerEntity.setId(customerDto.getId());
        customerEntity.setUserName(customerDto.getUserName());
        customerEntity.setEmail(customerDto.getEmail());
        CustomerFullName customerFullName = new CustomerFullName(customerDto.getFirstName(),customerDto.getLastName());
        customerEntity.setFullName(customerFullName);
        CustomerAddress address = new CustomerAddress(customerDto.getStreet(),customerDto.getCity(),customerDto.getDistrict());
        customerEntity.setAddress(address);
        customerEntity.setPostalCode(customerDto.getPostalCode());
        List <String> mobiles = new ArrayList<>();
        mobiles.add(customerDto.getMobile());
        customerEntity.setMobiles(mobiles);

        customerDao.update(customerEntity);
    }

    @Override
    public void deleteCustomer(CustomerDto customerDto) throws Exception {
        CustomerEntity customerEntity = new CustomerEntity();

        customerEntity.setId(customerDto.getId());
        customerEntity.setUserName(customerDto.getUserName());
        customerEntity.setEmail(customerDto.getEmail());
        CustomerFullName customerFullName = new CustomerFullName(customerDto.getFirstName(),customerDto.getLastName());
        customerEntity.setFullName(customerFullName);
        CustomerAddress address = new CustomerAddress(customerDto.getStreet(),customerDto.getCity(),customerDto.getDistrict());
        customerEntity.setAddress(address);
        customerEntity.setPostalCode(customerDto.getPostalCode());
        List <String> mobiles = new ArrayList<>();
        mobiles.add(customerDto.getMobile());
        customerEntity.setMobiles(mobiles);

        customerDao.delete(customerEntity);

    }

    @Override
    public List<CustomerDto> getAllCustomers() throws Exception {
       List<CustomerEntity> customerEntities = customerDao.getAll();
        List<CustomerDto> customerDtos = new ArrayList<>();

        for(CustomerEntity customerEntity : customerEntities){
            CustomerDto customerDto = new CustomerDto();
            customerDto.setId(customerEntity.getId());
            customerDto.setUserName(customerEntity.getUserName());
            customerDto.setEmail(customerEntity.getEmail());
            customerDto.setFirstName(customerEntity.getFullName().getFirstName());
            customerDto.setLastName(customerEntity.getFullName().getLastName());
            customerDto.setStreet(customerEntity.getAddress().getStreet());
            customerDto.setCity(customerEntity.getAddress().getCity());
            customerDto.setDistrict(customerEntity.getAddress().getDistrict());
            customerDto.setPostalCode(customerEntity.getPostalCode());
            customerDto.setMobile(customerEntity.getMobiles().toString());

            customerDtos.add(customerDto);
        }

        return customerDtos;
    }


}
