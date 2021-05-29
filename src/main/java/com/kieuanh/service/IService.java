package com.kieuanh.service;

import java.util.List;

public interface IService<T> {
    List<T> findAll();
    T findById(int id);
    void save(T p);
    void delete(int id);
    void edit(int id, T t);
}
