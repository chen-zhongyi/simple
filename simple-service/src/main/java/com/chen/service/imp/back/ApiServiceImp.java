package com.chen.service.imp.back;

import com.chen.dao.back.ApiRepository;
import com.chen.model.back.Api;
import com.chen.service.back.ApiService;
import com.chen.service.imp.BaseServiceImp;
import com.chen.vos.ApiVO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

@Service
public class ApiServiceImp extends BaseServiceImp<Api, ApiVO> implements ApiService {

    @Autowired
    private ApiRepository apiRepository;

    @Autowired
    private void setBaseRepository() {
        setBaseRepository(apiRepository);
    }

    @Override
    public Api voToModel(ApiVO vo) {
        Api api = new Api();
        api.setCreateTime(System.currentTimeMillis());
        return api;
    }

    @Override
    public Api voToModel(Api api, ApiVO vo) {
        api.setUpdateTime(System.currentTimeMillis());
        api.setMethod(vo.getMethod() != null ? vo.getMethod() : api.getMethod());
        api.setContentType(vo.getContentType() != null ? vo.getContentType() : api.getContentType());
        api.setHost(vo.getHost() != null ? vo.getHost() : api.getHost());
        api.setUri(vo.getUri() != null ? vo.getUri() : api.getUri());
        api.setHeaders(vo.getHeaders() != null ? vo.getHeaders() : api.getHeaders());
        api.setParams(vo.getParams() != null ? vo.getParams() : api.getParams());
        api.setAction(vo.getAction() != null ? vo.getAction() : api.getAction());
        api.setBody(vo.getBody() != null ? vo.getBody() : api.getBody());
        api.setExceptions(vo.getExceptions() != null ? vo.getExceptions() : api.getExceptions());
        api.setResult(vo.getResult() != null ? vo.getResult() : api.getResult());
        api.setStartTime(vo.getStartTime() != null ? vo.getStartTime() : api.getStartTime());
        api.setEndTime(vo.getEndTime() != null ? vo.getEndTime() : api.getEndTime());
        api.setExeTime(vo.getExeTime() != null ? vo.getExeTime() : api.getExeTime());
        api.setPersonId(vo.getPersonId() != null ? vo.getPersonId() : api.getPersonId());
        api.setPersonName(vo.getPersonName() != null ? vo.getPersonName() : api.getPersonName());
        api.setToken(vo.getToken() != null ? vo.getToken() : api.getToken());
        return api;
    }

    @Override
    public Page<Api> fetch(ApiVO vo, Sort sort) {
        Pageable pageable = new PageRequest(vo.getPage() - 1, vo.getSize(), sort);
        return apiRepository.findAll(buildSpecification(vo), pageable);
    }

    @Override
    public int count(ApiVO vo) {
        return (int) apiRepository.count(buildSpecification(vo));
    }

    private Specification<Api> buildSpecification(ApiVO vo) {
        return ((root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            predicates.add(criteriaBuilder.equal(root.get("deleted").as(Boolean.class), false));
            if (StringUtils.isNotBlank(vo.getAction())) {
                predicates.add(criteriaBuilder.like(root.get("action").as(String.class), "%" + vo.getAction() + "%"));
            }
            if (StringUtils.isNotBlank(vo.getPersonName())) {
                predicates.add(criteriaBuilder.like(root.get("personName").as(String.class), "%" + vo.getPersonName() + "%"));
            }
            if (vo.getStartTime() != null) {
                predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get("startTime").as(Long.class), vo.getStartTime()));
            }
            if (vo.getEndTime() != null) {
                predicates.add(criteriaBuilder.lessThanOrEqualTo(root.get("endTime").as(Long.class), vo.getEndTime()));
            }
            return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
        });
    }

}
