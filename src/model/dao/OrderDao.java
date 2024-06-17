package model.dao;

import model.entity.Customer;
import model.entity.Order;

import java.util.List;

public interface OrderDao {
    int addNewOrder(Order order);
    int updateOrder(Order order ,Integer id);
    int deleteOrder(Integer id);
    List<Order> getAllOrders();
    Order searchOrder(Integer id);
}
