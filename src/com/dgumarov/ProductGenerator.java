package com.dgumarov;

import com.dgumarov.model.Product;

import java.sql.*;
import java.util.*;

/**
 * Created by Dinar on 05.12.2016.
 */
public class ProductGenerator {

    Map<String, Integer> productList = new HashMap<>();

    public ProductGenerator() {
        productList.put("Battery charger", 20);
        productList.put("Usb cable", 10);
        productList.put("External powerbank", 30);
        productList.put("SD card", 25);
        productList.put("Protected case", 27);
    }

    public List<Product> generateInMemoryProductList()
    {
        List<Product> products = new ArrayList<>();

        Iterator<String> iterator = productList.keySet().iterator();

        int i = 0;

        while (iterator.hasNext())
        {
            String key = iterator.next();

            Product product = new Product();
            product.setId(i);
            product.setName(key);
            product.setPrice(productList.get(key));
            product.setQuantity(randomRange(2, 10));

            products.add(product);
            i++;
        }

        return  products;
    }

    public void generateDbProducts()
    {
        Connection connection;
        Statement statement;

        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:TEST.s3db");
            statement = connection.createStatement();
            statement.execute(createProductDb());

            Iterator<String> iterator = productList.keySet().iterator();
            int i = 0;

            while (iterator.hasNext())
            {
                String key = iterator.next();

                PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO products(name, price, quantity) VALUES(?,?,?)");
                preparedStatement.setString(1, key);
                preparedStatement.setInt(2, productList.get(key));
                preparedStatement.setInt(3, randomRange(2, 10));
                preparedStatement.execute();
                i++;
            }

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }



    }

    private String createProductDb()
    {
        return "CREATE TABLE if not exists 'products' ('id' INTEGER PRIMARY KEY AUTOINCREMENT, 'name' text, 'price' INT, 'quantity' INT);";
    }

    private int randomRange(int min, int max)
    {
        Random rand = new Random();
        return rand.nextInt((max - min) + 1) + min;
    }

}
