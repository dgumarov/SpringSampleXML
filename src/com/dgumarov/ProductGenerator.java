package com.dgumarov;

import com.dgumarov.model.Product;

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

    private int randomRange(int min, int max)
    {
        Random rand = new Random();
        return rand.nextInt((max - min) + 1) + min;
    }

}
