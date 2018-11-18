package com.chen.dao;

import com.chen.model.BaseModel;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.List;

@NoRepositoryBean
public interface BaseRepository<T extends BaseModel> extends CrudRepository<T, Long>, JpaSpecificationExecutor<T> {

    @Query("from #{#entityName} where deleted = false and id = ?1")
    T findByID(Long id);

    @Query("from #{#entityName} where deleted = false and id in(?1)")
    List<T> fetchByIds(List<Long> ids);

    @Query("from #{#entityName} where deleted = false")
    List<T> fetchAll();

}
