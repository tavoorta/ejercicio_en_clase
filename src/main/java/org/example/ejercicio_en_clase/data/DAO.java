package org.example.ejercicio_en_clase.data;

import java.util.List;

public interface  DAO<T> {

    List<T> findAll();

    T save(T entity);

    void update(T entity);

}
