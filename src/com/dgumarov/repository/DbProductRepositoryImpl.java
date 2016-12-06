package com.dgumarov.repository;

import com.dgumarov.ProductGenerator;
import com.dgumarov.model.Product;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dinar on 06.12.2016.
 */
public class DbProductRepositoryImpl implements ProductRepository {

    public DbProductRepositoryImpl() {
        ProductGenerator productGenerator = new ProductGenerator();
        productGenerator.generateDbProducts();
    }

    private List<Product> getFromDb(String sql)
    {
        List<Product> products = new ArrayList<>();

        try {
            Class.forName("org.sqlite.JDBC");
            Connection connection = DriverManager.getConnection("jdbc:sqlite:TEST.s3db");

            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next())
            {
                Product product = new Product();
                product.setId(resultSet.getInt(resultSet.findColumn("id")));
                product.setName(resultSet.getString(resultSet.findColumn("name")));
                product.setPrice(resultSet.getInt(resultSet.findColumn("price")));
                product.setQuantity(resultSet.getInt(resultSet.findColumn("quantity")));

                products.add(product);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return products;
    }

    @Override
    public List<Product> getAll() {
        return getFromDb("SELECT * FROM products");
    }

    @Override
    public List<Product> search(String searchString) {
        return getFromDb("SELECT * FROM products WHERE name LIKE '%"+searchString+"%'");
    }

    @Override
    public void sell(int id, int count) {

        Product product = getFromDb("SELECT * FROM products WHERE id="+id).get(0);

        if (count <= product.getQuantity())
            try {
                Class.forName("org.sqlite.JDBC");
                Connection connection = DriverManager.getConnection("jdbc:sqlite:TEST.s3db");

                PreparedStatement preparedStatement = connection.prepareStatement("UPDATE products SET quantity=? WHERE id=?");
                preparedStatement.setInt(1, product.getQuantity() - count);
                preparedStatement.setInt(2, id);
                preparedStatement.execute();

            } catch (SQLException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        else throw  new IllegalArgumentException("Quantity exceeded. There are only " + product.getQuantity() + " at the store.");
    }
}
