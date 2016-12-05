package com.dgumarov.repository;

import com.dgumarov.model.Product;

import java.util.List;

/**
 * Created by Dinar on 05.12.2016.
 */
public interface ProductRepository {

    List<Product> getAll();
    List<Product> search(String searchString);
    void sell(int id, int count);

}
