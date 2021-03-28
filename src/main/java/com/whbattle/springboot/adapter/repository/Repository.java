package com.whbattle.springboot.adapter.repository;

import java.util.List;

public interface Repository<T, R> {

    List<T> read();

    T readById(R id);

    T create(R id, T entity);

    T update(R id, T entity);

    T delete(R id);
}