package controller;

import exception.CatchException;
import model.dto.OrderDto;
import model.entity.Customer;
import model.entity.Order;
import model.entity.Product;
import model.service.OrderService;
import model.service.OrderServiceImpl;

import java.sql.Date;
import java.time.LocalDate;
import java.util.*;

public class OrderController {
    private final OrderService orderService =new OrderServiceImpl();
    public List<OrderDto>getAllOrders() throws CatchException {
        return orderService.queryAllOrders();
    }
    public void insertOrder() throws CatchException {
        System.out.print("[+] Order Name: ");
        String orderName = new Scanner(System.in).nextLine();
        System.out.print("[+] Order Description: ");
        String orderDescription = new Scanner(System.in).nextLine();
        System.out.print("[+] Customer ID: ");
        int customerId = new Scanner(System.in).nextInt();
        System.out.print("[+] Product ID: ");
        int productId = new Scanner(System.in).nextInt();
        new OrderServiceImpl().insertOrder(Order.builder()
                .id(new Random().nextInt(100))
                .orderName(orderName)
                .orderDescription(orderDescription)
                .orderDate(Date.valueOf(LocalDate.now()))
                .customer(Customer.builder()
                        .id(customerId)
                        .build())
                .products(new ArrayList<>(
                        List.of(Product.builder()
                                .id(productId)
                                .build())
                ))
                .build());
    }
    public void deleteOrder() throws CatchException {
        System.out.print("[+] insert ID:");
        Integer id = new Scanner(System.in).nextInt();
        orderService.deleteOrderById(id);
    }
    public void updateProduct() throws CatchException {
        System.out.print("[+] Insert ID to update product:");
        Integer id = new Scanner(System.in).nextInt();
        orderService.updateOrder(id);
    }
    public OrderDto searchOrder() throws CatchException {
        System.out.print("[+] insert ID to search Product :");
        Integer id = new Scanner(System.in).nextInt();
        return orderService.searchOrders(id);
    }
}
