package model.service;

import exception.CatchException;
import mapper.Mapper;
import model.dao.ProductDao;
import model.dao.ProductDaolmpl;
import model.dto.ProductDto;
import model.entity.Customer;
import model.entity.Product;

import java.util.List;
import java.util.Scanner;

public class ProductServiceImpl implements ProductService {
    private final ProductDao productDao = new ProductDaolmpl();

    @Override
    public void insertProduct(Product product) {
        productDao.insertProduct(product);
    }

    @Override
    public List<ProductDto> queryAllProduct() {
        try {
            List<Product> products = productDao.queryAllProduct();
            if(!(products.isEmpty())){
                return productDao.queryAllProduct().stream().map(element -> Mapper.mapProductToProductDto(element)).toList();

            }else {
                throw new CatchException("[+] No for Data");
            }
        }catch (CatchException e){
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public void deleteProduct(Integer id) throws CatchException {
        try {
            if (productDao.deleteProduct(id) > 0) {
                productDao.deleteProduct(id);
            } else {
                throw new CatchException("Data failed");
            }
        } catch (CatchException e) {
            System.out.println(e.getMessage());
        }
    }
    @Override
    public void updateProduct(Integer id) throws CatchException {
        Product product = productDao.searchProduct(id);
        try {
            if (product == null) {
                throw new CatchException("Data is not found");
            } else {
                System.out.print("[+] Insert ProductName:");
                String productname = new Scanner(System.in).nextLine();
                System.out.print("[+] Insert ProductCode:");
                String productcode = new Scanner(System.in).nextLine();
                System.out.print("[+] Insert Description:");
                String description = new Scanner(System.in).nextLine();
                product.setProductName(productname);
                product.setProductCode(productcode);
                product.setProductDescription(description);
                productDao.updateProduct(product, id);
                System.out.println("Update is Successful");
            }
        } catch (CatchException e) {
            System.out.println(e.getMessage());
        }
    }
    @Override
    public ProductDto searchProduct(Integer id) throws CatchException {
        try {
            if (productDao.searchProduct(id) == null) {
                throw new CatchException("data not found");
            }
            else
            {
               return Mapper.mapProductToProductDto(productDao.searchProduct(id));
            }
        }catch (CatchException e)
        {
            System.out.println(e.getMessage());
        }
       return  null;
    }
}
