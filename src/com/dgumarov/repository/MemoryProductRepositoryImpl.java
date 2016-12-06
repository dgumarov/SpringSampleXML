package com.dgumarov.repository;

import com.dgumarov.ProductGenerator;
import com.dgumarov.model.Product;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dinar on 05.12.2016.
 */
public class MemoryProductRepositoryImpl implements ProductRepository {

    private List<Product> products = new ArrayList<>();

    public MemoryProductRepositoryImpl() {
        ProductGenerator productGenerator = new ProductGenerator();
        products = productGenerator.generateInMemoryProductList();
    }

    @Override
    public List<Product> getAll() {
        return products;
    }

    @Override
    public List<Product> search(String searchString) {

        List<Product> searchResult = new ArrayList<>();

        products.forEach(product -> {
            if (product.getName().contains(searchString))
                searchResult.add(product);
        });

        return searchResult;
    }

    @Override
    public void sell(int id, int count) {
        products.forEach(product -> {
            if (product.getId()== id)
            {
                if (count <= product.getQuantity())
                    product.setQuantity(product.getQuantity() - count);
                else throw new IllegalArgumentException("Quantity exceeded. There are only " + product.getQuantity() + " at the store.");
            }
        });
    }
}
