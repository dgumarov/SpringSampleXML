package com.dgumarov;

import com.dgumarov.model.Product;
import com.dgumarov.service.ProductService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import com.dgumarov.service.ConsumerService;

import java.util.List;

/**
 * Created by user on 01.12.16.
 */
public class Application {
    public static void main(String[] args) {
        // Single responsibility

        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");

        //ConsumerService consumerService = applicationContext.getBean("consumerService", ConsumerService.class);
        //System.out.println(consumerService.findAll().get(0).getName());

        ProductService productService = applicationContext.getBean("productService", ProductService.class);

        List<Product> products = productService.findAll();

        products.forEach(product -> System.out.println(product));

        productService.sell(2, 10);

    }
}
