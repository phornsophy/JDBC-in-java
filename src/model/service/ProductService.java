package model.service;

import exception.CatchException;
import model.dto.ProductDto;
import model.entity.Product;

import java.util.List;

public interface ProductService {
    void insertProduct(Product product);
    List <ProductDto> queryAllProduct();
    void deleteProduct(Integer id) throws CatchException;
    void updateProduct(Integer id) throws CatchException;
    ProductDto searchProduct(Integer id) throws CatchException;
}
