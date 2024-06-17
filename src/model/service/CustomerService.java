package model.service;

import exception.CatchException;
import model.dto.CustomerDto;
import model.entity.Customer;

import java.util.List;

public interface CustomerService {
    List<CustomerDto> queryAllCustomers();
    void insertCustomer(Customer customer);
    void deleteCustomer(Integer id) throws CatchException;
    void updateCustomer(Integer id) throws CatchException;
    CustomerDto searchCustomer(Integer id) throws CatchException;
}
