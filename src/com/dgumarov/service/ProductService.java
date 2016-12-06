package com.dgumarov.service;

import com.dgumarov.model.Product;

import java.util.List;

/**
 * Created by Dinar on 06.12.2016.
 */
public interface ProductService {
    List<Product> findAll();
    List<Product> findByName(String name);
    void sell (int id, int count);
}
