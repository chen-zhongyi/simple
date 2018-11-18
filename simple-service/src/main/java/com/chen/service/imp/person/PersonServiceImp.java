package com.chen.service.imp.person;

import com.chen.dao.person.PersonRepository;
import com.chen.enums.LoginType;
import com.chen.enums.PersonType;
import com.chen.enums.Sex;
import com.chen.model.person.Person;
import com.chen.service.imp.BaseServiceImp;
import com.chen.service.person.PersonService;
import com.chen.utils.JwtUtil;
import com.chen.utils.PinyinUtil;
import com.chen.utils.PropertyUtil;
import com.chen.vos.PersonVO;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.InitializingBean;
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
public class PersonServiceImp extends BaseServiceImp<Person, PersonVO> implements PersonService, InitializingBean {

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private void setBaseRepository() {
        setBaseRepository(personRepository);
    }

    @Autowired
    private PropertyUtil propertyUtil;

    public Person login(LoginType type, PersonType personType, String username, String phone, String password) {
        boolean success = false;
        Person person = null;
        if (type == LoginType.USERNAME) {
            person = personRepository.findByTypeAndUsername(personType, username);
            success = person.getPassword().equals(password);
        } else if (type == LoginType.PHONE) {

        }
        if (success) {
            PersonVO personVO = new PersonVO();
            personVO.setLastLoginTime(System.currentTimeMillis());
            if (person.getFirstLoginTime() == null) {
                personVO.setFirstLoginTime(System.currentTimeMillis());
            }
            Integer loginAmount = person.getLoginAmount();
            if (loginAmount == null)    loginAmount = 0;
            loginAmount ++;
            personVO.setLoginAmount(loginAmount);
            Person p = edit(person.getId(), personVO);
            p.setToken(JwtUtil.create(person, propertyUtil.getSecret(), propertyUtil.getHost()));
            return p;
        }
        return null;
    }

    public void logout(Person person) {

    }

    public void changePassword(Person person, String password) {
        person.setPassword(password);
        personRepository.save(person);
    }

    @Override
    public void afterPropertiesSet() {
        if (fetchAll().size() <= 0) {
            PersonVO personVO = new PersonVO();
            personVO.setType(PersonType.BACK.code());
            personVO.setUsername("admin");
            personVO.setName("admin");
            personVO.setPhone("18758324519");
            changePassword(add(personVO), DigestUtils.md5Hex("123456"));
        }
    }

    @Override
    public Person voToModel(PersonVO vo) {
        Person person = new Person();
        person.setType(PersonType.convert(vo.getType()));
        person.setUsername(vo.getUsername());
        person.setPhone(vo.getPhone());
        person.setEmail(vo.getEmail());
        return person;
    }

    @Override
    public Person voToModel(Person person, PersonVO vo) {
        person.setName(vo.getName() != null ? vo.getName() : person.getName());
        person.setPinyin(PinyinUtil.getFirstSpell(person.getName()) + PinyinUtil.getPinYin(person.getName()));
        person.setNickname(vo.getNickname() != null ? vo.getNickname() : person.getNickname());
        person.setZone(vo.getZone() != null ? vo.getZone() : person.getZone());
        person.setAvatar(vo.getAvatar() != null ? vo.getAvatar() : person.getAvatar());
        person.setBirthday(vo.getBirthday() != null ? vo.getBirthday() : person.getBirthday());
        person.setIntro(vo.getIntro() != null ? vo.getIntro() : person.getIntro());
        person.setRemark(vo.getRemark() != null ? vo.getRemark() : person.getRemark());
        person.setIdCard(vo.getIdCard() != null ? vo.getIdCard() : person.getIdCard());
        person.setSex(vo.getSex() != null ? Sex.convert(vo.getSex()) : person.getSex());
        person.setFirstLoginTime(vo.getFirstLoginTime() != null ? vo.getFirstLoginTime() : person.getFirstLoginTime());
        person.setLastLoginTime(vo.getLastLoginTime() != null ? vo.getLastLoginTime() : person.getLastLoginTime());
        person.setLoginAmount(vo.getLoginAmount() != null ? vo.getLoginAmount() : person.getLoginAmount());
        return person;
    }

    public boolean isExist(String phone) {
        return personRepository.findByPhone(phone) != null;
    }

    public Page<Person> fetch(PersonVO vo, Sort sort) {
        Pageable pageable = new PageRequest(vo.getPage() - 1, vo.getSize(), sort);
        return personRepository.findAll(buildSpecification(vo), pageable);
    }

    public int count(PersonVO vo) {
        return (int) personRepository.count(buildSpecification(vo));
    }

    private Specification<Person> buildSpecification(PersonVO vo) {
        return ((root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            predicates.add(criteriaBuilder.equal(root.get("deleted").as(Boolean.class), false));
            if (StringUtils.isNotBlank(vo.getName())) {
                predicates.add(criteriaBuilder.like(root.get("name").as(String.class), "%" + vo.getName() + "%"));
            }
            return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
        });
    }

}
