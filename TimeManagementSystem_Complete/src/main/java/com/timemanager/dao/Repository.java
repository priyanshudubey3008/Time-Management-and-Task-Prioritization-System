package com.timemanager.dao;

import java.util.List;

public interface Repository<T, ID> {
    T findById(ID id) throws Exception;
    List<T> findAll() throws Exception;
    boolean delete(ID id) throws Exception;
}
