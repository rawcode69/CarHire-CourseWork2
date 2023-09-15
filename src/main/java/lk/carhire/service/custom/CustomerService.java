package lk.carhire.service.custom;

import lk.carhire.dto.CustomerDto;
import lk.carhire.service.SuperService;

import java.util.List;

public interface CustomerService extends SuperService {


    Integer saveCustomer(CustomerDto customerDto) throws Exception;

    CustomerDto getCustomer(Integer id) throws  Exception;

    void updateCustomer(CustomerDto customerDto) throws Exception;


    void deleteCustomer(CustomerDto customerDto) throws Exception;


    List<CustomerDto> getAllCustomers() throws Exception;
}
