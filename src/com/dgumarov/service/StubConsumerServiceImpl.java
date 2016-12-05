package com.dgumarov.service;

import com.dgumarov.repository.ConsumerRepository;
import com.dgumarov.model.Consumer;

import java.util.List;

/**
 * Created by user on 01.12.16.
 */
public class StubConsumerServiceImpl implements ConsumerService {
    private ConsumerRepository repository;

    /*
    public StubConsumerServiceImpl(ConsumerRepository repository) {
        this.repository = repository;
    }
    */

    public void setRepository(ConsumerRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Consumer> findAll(/* Criteria */) {
        return repository.getAll();
    }
}
