package model.dao;

import model.entity.Customer;
import model.entity.Product;

import java.util.List;

public interface ProductDao {
   List<Product> queryAllProduct();
   int insertProduct(Product product);
   int  deleteProduct(Integer id);
   Product searchProduct(Integer id);
   int updateProduct( Product product ,Integer id);
}
