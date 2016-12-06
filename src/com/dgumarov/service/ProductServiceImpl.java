package com.dgumarov.service;

import com.dgumarov.model.Product;
import com.dgumarov.repository.ProductRepository;

import java.util.List;

/**
 * Created by Dinar on 06.12.2016.
 */
public class ProductServiceImpl implements ProductService {
    private ProductRepository productRepository;

    public ProductServiceImpl() {}

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public void setProductRepository(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public List<Product> findAll() {
        return productRepository.getAll();
    }

    @Override
    public List<Product> findByName(String name) {
        return productRepository.search(name);
    }

    @Override
    public void sell(int id, int count) {
        productRepository.sell(id, count);
    }
}
