package com.chen.service.person;

import com.chen.enums.LoginType;
import com.chen.enums.PersonType;
import com.chen.model.person.Person;
import com.chen.service.BaseService;
import com.chen.vos.PersonVO;

public interface PersonService extends BaseService<Person, PersonVO> {

    Person login(LoginType type, PersonType personType, String username, String phone, String password);

    void logout(Person person);

    void changePassword(Person person, String password);

    boolean isExist(String phone);

}
