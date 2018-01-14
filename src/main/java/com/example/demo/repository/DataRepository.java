package com.example.demo.repository;

import com.example.demo.entity.DomainObject;

import java.util.List;

public interface DataRepository <V extends DomainObject>{

    void put(V entity);

    void delete(V entity);

    List<V> getAll();

}
