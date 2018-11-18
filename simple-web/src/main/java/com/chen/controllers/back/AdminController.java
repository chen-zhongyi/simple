package com.chen.controllers.back;

import com.chen.annotations.NotNeedLogin;
import com.chen.controllers.BaseController;
import com.chen.enums.LoginType;
import com.chen.enums.PersonType;
import com.chen.model.person.Person;
import com.chen.service.person.PersonService;
import com.chen.vos.PersonVO;
import com.chen.vos.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/back/admin")
public class AdminController extends BaseController {

    @Autowired
    private PersonService personService;

    @PostMapping("/login")
    @NotNeedLogin
    public Result login(@RequestBody PersonVO vo) {
        Person person = personService.login(LoginType.USERNAME, PersonType.BACK, vo.getUsername(), null, vo.getPassword());
        PersonVO personVO = new PersonVO(person);
        return Result.success(personVO);
    }
}
