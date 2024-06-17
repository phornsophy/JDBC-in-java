
package model.service;
import exception.CatchException;
import mapper.Mapper;
import model.dao.OrderDao;
import model.dao.OrderDaoImpl;
import model.dto.OrderDto;
import model.entity.Order;

import java.util.List;
import java.util.Scanner;

public class OrderServiceImpl implements OrderService{
    private final OrderDao orderDao = new OrderDaoImpl();
    @Override
    public List<OrderDto> queryAllOrders(){
        try {
            List<Order> orders = orderDao.getAllOrders();
            if(!(orders.isEmpty())){
                return orderDao.getAllOrders().stream().map(Mapper::mapOrderToOrderDto).toList();
            }else {
                throw new CatchException("No Data !");
            }
        }catch (CatchException e){
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public void insertOrder(Order order) throws CatchException {
        orderDao.addNewOrder(order);
    }
    @Override
    public void updateOrder(Integer id) throws CatchException {
        try {
            Order order = orderDao.searchOrder(id);
            if( order == null){
                throw new CatchException("Data is not found");
            }else {
                System.out.print("[+] Insert OrderName:");
                String name = new Scanner(System.in).nextLine();
                System.out.print("[+] OrderDescription: :");
                String description = new Scanner(System.in).nextLine();
                System.out.print("[+] OrderBio:");
                String bio = new Scanner(System.in).nextLine();
                order.setOrderName(name);
                order.setOrderDescription(description);
                order.setBio(bio);
                orderDao.updateOrder(order, id);
                System.out.println("Update Order Success!");
            }
        }catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }


    @Override
    public void deleteOrderById(Integer id) {
        try {
            if(orderDao.deleteOrder(id)>0) {
                throw new CatchException("Order Deleted Successfully !");
            }else {
                throw new CatchException("Cant delete order");
            }
        }catch (CatchException e){
            System.out.println(e.getMessage());
        }
    }

    @Override
    public OrderDto searchOrders(Integer id) throws CatchException {
        try {
            if (orderDao.searchOrder(id) == null) {
                throw new CatchException("data not found");
            }
            else
            {
                return Mapper.mapOrderToOrderDto(orderDao.searchOrder(id));
            }
        }catch (CatchException e)
        {
            System.out.println(e.getMessage());
        } return  null;

    }
    }
