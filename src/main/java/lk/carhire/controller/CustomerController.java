package lk.carhire.controller;

import lk.carhire.dto.CustomerDto;
import lk.carhire.service.ServiceFactory;
import lk.carhire.service.custom.CustomerService;

import java.util.List;

public class CustomerController {

CustomerService customerService = (CustomerService) ServiceFactory.getInstance().getService(ServiceFactory.serviceType.CUSTOMER);
    public Integer saveCustomer(CustomerDto customerDto) throws Exception {

        return customerService.saveCustomer(customerDto);

    }

    public CustomerDto getCustomer(Integer id) throws Exception{
      return customerService.getCustomer(id);

    }

    public void updateCustomer(CustomerDto customerDto) throws Exception {
        customerService.updateCustomer(customerDto);
    }

    public void deleteCustomer(CustomerDto customerDto) throws Exception {
        customerService.deleteCustomer(customerDto);
    }


    public List<CustomerDto> getAllCustomer() throws Exception {
       return customerService.getAllCustomers();
    }
}
