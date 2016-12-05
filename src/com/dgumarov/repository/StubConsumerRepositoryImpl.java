package com.dgumarov.repository;

import com.dgumarov.model.Consumer;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by user on 01.12.16.
 */
public class StubConsumerRepositoryImpl implements ConsumerRepository {
    List<Consumer> consumers = new ArrayList<>();

    public StubConsumerRepositoryImpl() {
        Consumer consumer = new Consumer();
        consumer.setName("Vasya");
        consumer.setAge(20);
        consumer.setStatus("gold");

        consumers.add(consumer);
    }

    @Override
    public void add(Consumer consumer) {
        consumers.add(consumer);
    }

    @Override
    public void update(Consumer consumer) {
        // TODO: придумать как обновить конкретного пользователя
    }

    @Override
    public void delete(Consumer consumer) {
        consumers.remove(consumer);
    }

    @Override
    public List<Consumer> getAll() {
        return consumers;
    }
}
