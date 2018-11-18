package com.chen.service.imp;

import com.chen.dao.BaseRepository;
import com.chen.model.BaseModel;
import com.chen.service.BaseService;
import com.chen.vos.OneData;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

public class BaseServiceImp<T extends BaseModel, E extends OneData> implements BaseService<T, E> {

    private BaseRepository<T> baseRepository;

    public void setBaseRepository(BaseRepository baseRepository) {
        this.baseRepository = baseRepository;
    }

    public T voToModel(E e) {
        return null;
    }

    public T voToModel(T t, E e) {
        return t;
    }

    public T add(E e) {
        T t = voToModel(e);
        return baseRepository.save(voToModel(t, e));
    }

    public T edit(Long id, E e) {
        T t = baseRepository.findByID(id);
        t.setUpdateTime(System.currentTimeMillis());
        return baseRepository.save(voToModel(t, e));
    }

    public T info(Long id) {
        return baseRepository.findByID(id);
    }

    public void logicDelete(Long id) {
        T t = baseRepository.findByID(id);
        t.setDeleted(true);
        t.setUpdateTime(System.currentTimeMillis());
        baseRepository.save(t);
    }

    public List<T> fetchAll() {
        return baseRepository.fetchAll();
    }

    public Page<T> fetch(E e, Sort sort) {
        Specification<T> specification = ((root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            predicates.add(criteriaBuilder.equal(root.get("deleted").as(Boolean.class), false));
            return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
        });
        return baseRepository.findAll(specification, new PageRequest(e.getPage() - 1, e.getSize(), sort));
    }

    public int count(E e) {
        Specification<T> specification = ((root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            predicates.add(criteriaBuilder.equal(root.get("deleted").as(Boolean.class), false));
            return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
        });
        return (int) baseRepository.count(specification);
    }

}
