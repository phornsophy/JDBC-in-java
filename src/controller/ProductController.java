package controller;

import exception.CatchException;
import model.dto.ProductDto;
import model.entity.Product;
import model.service.ProductService;
import model.service.ProductServiceImpl;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

public class ProductController {
    private final ProductService productService =new ProductServiceImpl();
    public List<ProductDto>getAllProduct(){
        return productService.queryAllProduct();
    }
    public void insertProduct(){
        System.out.print("[+] insert Product Name:");
        String name = new Scanner(System.in).nextLine().toLowerCase(Locale.ROOT);
        System.out.print("[+] insert Product Code:");
        String code = new Scanner(System.in).nextLine().toLowerCase(Locale.ROOT);
        System.out.print("[+] insert Product importAt:");
        String importDate = new Scanner(System.in).nextLine().toLowerCase(Locale.ROOT);
        System.out.print("[+] insert Product description:");
        String description = new Scanner(System.in).nextLine().toLowerCase(Locale.ROOT);
        Product product;
        product = Product.builder()
                .productName(name)
                .productCode(code)
                .isDeleted(false)
                .importedDate(Date.valueOf(importDate))
                .expireddDate(Date.valueOf(LocalDate.now()))
                .productDescription(description)
                .build();
        productService.insertProduct(product);
    }
    public void deleteProduct() throws CatchException {
        System.out.print("[+] insert ID:");
        Integer id = new Scanner(System.in).nextInt();
        productService.deleteProduct(id);
    }
    public void updateProduct() throws CatchException {
        System.out.print("[+] Insert ID to search product:");
        Integer id = new Scanner(System.in).nextInt();
        productService.updateProduct(id);
    }
    public ProductDto searchProduct() throws CatchException {
         System.out.print("[+] insert ID to search Product :");
         Integer id = new Scanner(System.in).nextInt();
         return productService.searchProduct(id);
    }
}
