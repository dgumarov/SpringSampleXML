package com.dgumarov;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import com.dgumarov.service.ConsumerService;

/**
 * Created by user on 01.12.16.
 */
public class Application {
    public static void main(String[] args) {
        // Single responsibility

        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
        ConsumerService consumerService = applicationContext.getBean("consumerService", ConsumerService.class);

        System.out.println(consumerService.findAll().get(0).getName());
    }
}
