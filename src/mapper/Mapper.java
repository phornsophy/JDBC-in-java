package mapper;

import model.dto.CustomerDto;
import model.dto.OrderDto;
import model.dto.ProductDto;
import model.entity.Customer;
import model.entity.Order;
import model.entity.Product;

public class Mapper {
    public static CustomerDto mapCustomerToCustomerDto(Customer customer) {
        if (customer == null) {
            return null;
        }
        return new CustomerDto(customer.getId(),customer.getName(),customer.getEmail(),customer.getIsDeleted(),customer.getCreatedAt());
    }
    public static ProductDto mapProductToProductDto(Product product) {
        if (product==null)
        {
            return null;
        }
        return new ProductDto(product.getId(),product.getProductName(), product.getIsDeleted(),product.getImportedDate(),product.getExpireddDate(),product.getProductDescription());
    }
    public static OrderDto mapOrderToOrderDto(Order order) {
        if (order == null)
        {
            return null;
        }
        return  OrderDto.builder()
                .id(order.getId())
                .orderName(order.getOrderName())
                .orderDescription(order.getOrderDescription())
                .bio(order.getBio())
                .customer(CustomerDto.builder()
                        .id(order.getCustomer().getId())
                        .name(order.getCustomer().getName())
                        .email(order.getCustomer().getEmail())
                        .is_deleted(order.getCustomer().getIsDeleted())
                        .created_date(order.getCustomer().getCreatedAt())
                        .build())
                .build();
    }
}
