package com.yaminsky.bankspringhibernate.service;

import org.springframework.hateoas.CollectionModel;

public interface IGeneralService<T, ID> {
    CollectionModel<T> findAll();

    T findById(ID id);

    T create(T entity);

    void update(ID id, T entity);

    void delete(ID id);
}
