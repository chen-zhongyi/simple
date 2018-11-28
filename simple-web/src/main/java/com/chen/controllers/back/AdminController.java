package com.chen.controllers.back;

import com.chen.annotations.NotNeedLogin;
import com.chen.controllers.BaseController;
import com.chen.enums.LoginType;
import com.chen.enums.PersonType;
import com.chen.model.person.Person;
import com.chen.service.person.PersonService;
import com.chen.vos.PersonVO;
import com.chen.vos.Result;
import com.chen.vos.StringVO;
import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/back/admin")
public class AdminController extends BaseController {

    private static final Logger logger = LoggerFactory.getLogger(AdminController.class);

    @Autowired
    private PersonService personService;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private RedisTemplate redisTemplate;

    @PostMapping("/login")
    @NotNeedLogin
    public Result login(@RequestBody PersonVO vo) {
        Person person = personService.login(LoginType.USERNAME, PersonType.BACK, vo.getUsername(), null, vo.getPassword());
        PersonVO personVO = new PersonVO(person);
        return Result.success(personVO);
    }

    @PostMapping("/set")
    @NotNeedLogin
    public Result set(@RequestParam String key, @RequestParam String value) {
        Person person = personService.info(12L);
        redisTemplate.opsForValue().set("person:" + person.getId(), person);
        Person temp = (Person) redisTemplate.opsForValue().get("person:" + person.getId());
        logger.info("person:" + new Gson().toJson(temp));
        stringRedisTemplate.opsForValue().set(key, value);
        return Result.success();
    }

    @GetMapping("/get")
    @NotNeedLogin
    public Result get(@RequestParam String key) {
        String value = stringRedisTemplate.opsForValue().get(key);
        stringRedisTemplate.opsForHash();
        return Result.success(new StringVO(value));
    }
}
