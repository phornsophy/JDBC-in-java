package model.service;

import exception.CatchException;
import mapper.Mapper;
import model.dao.CustomerDao;
import model.dao.CustomerDaoImpl;
import model.dto.CustomerDto;
import model.entity.Customer;

import java.util.List;
import java.util.Scanner;

public class CustomerServiceImpl implements CustomerService{
    private final CustomerDao customerDao = new CustomerDaoImpl();
    @Override
    public List<CustomerDto> queryAllCustomers() {
        try {
            List<Customer> customers = customerDao.queryAllCustomer();
            if(!(customers.isEmpty())){
                return customerDao.queryAllCustomer().stream()
                        .map(Mapper::mapCustomerToCustomerDto)
                        .toList();
            }else {
                throw new CatchException("[+] No for Data");
            }
        }catch (CatchException e){
            System.out.println(e.getMessage());
        }
        return null;
    }
    @Override
    public void insertCustomer(Customer customer) {
        customerDao.insertCustomer(customer);
    }
    @Override
    public void deleteCustomer(Integer id) {
        try {
            if(customerDao.deleteCustomer(id) > 0)
            {
                customerDao.deleteCustomer(id);
                throw new CatchException("Delete Customer Success !");
            }
           else
            {
                throw new CatchException(" Data isn't not delete !");
            }
        } catch (CatchException e) {
            System.out.println(e.getMessage());
        }
    }
    @Override
    public void updateCustomer(Integer id){
        try {
             Customer customers = customerDao.searchCustomerById(id);
            if( customers.getName()== null){
                throw new CatchException("Data is not found");
            }else {
                System.out.print("[+] Insert name:");
                String name = new Scanner(System.in).nextLine();
                System.out.print("[+] Insert email:");
                String email = new Scanner(System.in).nextLine();
                System.out.print("[+] Insert password:");
                String password = new Scanner(System.in).nextLine();
                customers.setName(name);
                customers.setEmail(email);
                customers.setPassword(password);
                customerDao.updateCustomer(customers, id);
                System.out.println("Update customer id is Success");
            }
        }catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    @Override
    public CustomerDto searchCustomer(Integer id)  {
        try{
            if(customerDao.searchCustomerById(id) == null)
            {
                throw new CatchException("data not found");
            }else
            {
                return Mapper.mapCustomerToCustomerDto(customerDao.searchCustomerById(id));
            }
        }catch (CatchException e)
        {
            System.out.println(e.getMessage());
        }
        return null;
    };

}
