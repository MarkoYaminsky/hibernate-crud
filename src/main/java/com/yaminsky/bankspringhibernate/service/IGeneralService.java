package com.yaminsky.bankspringhibernate.service;

import java.util.List;

public interface IGeneralService<T, ID> {
    List<T> findAll();

    T findById(ID id);

    T create(T entity);

    void update(ID id, T entity);

    void delete(ID id);
}
