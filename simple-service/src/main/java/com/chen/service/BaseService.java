package com.chen.service;

import com.chen.model.BaseModel;
import com.chen.vos.OneData;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;

import java.util.List;

public interface BaseService<T extends BaseModel, E extends OneData> {

    T voToModel(E e);

    T voToModel(T t, E e);

    T add(E e);

    T edit(Long id, E e);

    T info(Long id);

    void logicDelete(Long id);

    List<T> fetchAll();

    Page<T> fetch(E e, Sort sort);

    int count(E e);

}
