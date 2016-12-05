package com.dgumarov.repository;

import com.dgumarov.model.Consumer;

import java.util.List;

/**
 * Created by user on 01.12.16.
 */
public interface ConsumerRepository {
    void add(Consumer consumer);
    void update(Consumer consumer);
    void delete(Consumer consumer);
    List<Consumer> getAll();
}
