package model.dao;

import model.entity.Product;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ProductDaolmpl implements ProductDao{

    @Override
    public List<Product> queryAllProduct() {
        String sql = """
                SELECT * FROM "product" 
                """;
        try(
                Connection connection = DriverManager.getConnection(

                        "jdbc:postgresql://localhost:5433/postgres",
                        "postgres",
                        "Gjk1217&9!"
                );
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
                ){
            ResultSet resultSet = preparedStatement.executeQuery();
            List<Product> products = new ArrayList<>();
            while (resultSet.next()) {
                products.add(
                        Product.builder()
                                .id(resultSet.getInt("id"))
                                .productName(resultSet.getString("product_name"))
                                .productCode(resultSet.getString("product_code"))
                                .isDeleted(resultSet.getBoolean("is_deleted"))
                                .importedDate(resultSet.getDate("imported_at"))
                                .expireddDate(resultSet.getDate("expired_at"))
                                .productDescription(resultSet.getString("product_description"))
                                .build()
                );
            }
            return  products;
        }catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
    @Override
    public int insertProduct(Product product) {
        String sql = """
                INSERT INTO "product" (product_name,product_code, is_deleted, imported_at,expired_at, product_description)
                VALUES (? , ? , ? , ? ,? ,?)
                """;
        try(
                Connection connection = DriverManager.getConnection(

                        "jdbc:postgresql://localhost:5433/postgres",
                        "postgres",
                        "Gjk1217&9!"
                );
                PreparedStatement preparedStatement = connection.prepareStatement(sql)
                )
        {
            preparedStatement.setString(1, product.getProductName());
            preparedStatement.setString(2, product.getProductCode());
            preparedStatement.setBoolean(3, product.getIsDeleted());
            preparedStatement.setDate(4, product.getImportedDate());
            preparedStatement.setDate(5,product.getExpireddDate());
            preparedStatement.setString(6, product.getProductDescription());
            int rowAffected = preparedStatement.executeUpdate();
            String message =rowAffected > 0 ? "Data insert to database is successfully" : "Data insert failed";
            System.out.println(message);
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return 0;
    }

    @Override
    public int deleteProduct(Integer id) {
        String sql = """
                DELETE FROM "product"
                WHERE id = ?
                """;
        try(
                Connection connection = DriverManager.getConnection(

                        "jdbc:postgresql://localhost:5433/postgres",
                        "postgres",
                        "Gjk1217&9!"
                );
                PreparedStatement preparedStatement = connection.prepareStatement(sql)
                ){
                preparedStatement.setInt(1, id);
                int rowAffected = preparedStatement.executeUpdate();
                return rowAffected;
        }catch (SQLException e)
        {
            System.out.println(e.getMessage());
        }
        return 0;
    }

    @Override
    public Product searchProduct(Integer id) {
        String sql = """
                SELECT * FROM product
                WHERE id = ?
                """;
        try(
                Connection connection = DriverManager.getConnection(

                        "jdbc:postgresql://localhost:5433/postgres",
                        "postgres",
                        "Gjk1217&9!"
                );
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
                ){
               preparedStatement.setInt(1, id);
               ResultSet resultSet = preparedStatement.executeQuery();
               Product products = null;
               while (resultSet.next()) {
                   products = Product.builder()
                           .id(resultSet.getInt("id"))
                           .productName(resultSet.getString("product_name"))
                           .productCode(resultSet.getString("product_code"))
                           .isDeleted(resultSet.getBoolean("is_deleted"))
                           .importedDate(resultSet.getDate("imported_at"))
                           .expireddDate(resultSet.getDate("expired_at"))
                           .productDescription(resultSet.getString("product_description"))
                           .build();
               }
               return  products;
        }catch (SQLException e)
        {
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public int updateProduct(Product product , Integer id) {
        String sql = """
                UPDATE "product" SET product_name = ? , product_code= ? , product_description = ?
                WHERE  id = ?
                """;
        try(
                Connection connection = DriverManager.getConnection(

                        "jdbc:postgresql://localhost:5433/postgres",
                        "postgres",
                        "Gjk1217&9!"
                );
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
                )
        {
                preparedStatement.setString(1,product.getProductName());
                preparedStatement.setString(2,product.getProductCode());
                preparedStatement.setString(3,product.getProductDescription());
                preparedStatement.setInt(4, id);
               int rowAffected = preparedStatement.executeUpdate();
               return rowAffected;
        }
        catch (SQLException e)
        {
            System.out.println(e.getMessage());
        }
        return 0;
    }
}
