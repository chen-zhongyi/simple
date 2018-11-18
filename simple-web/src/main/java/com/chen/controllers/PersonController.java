package com.chen.controllers;

import com.chen.model.person.Person;
import com.chen.service.person.PersonService;
import com.chen.vos.PageData;
import com.chen.vos.PersonVO;
import com.chen.vos.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/admin/person")
public class PersonController {

    private Logger logger = LoggerFactory.getLogger(PersonController.class);

    @Autowired
    private PersonService personService;

    @GetMapping
    public Result list(PersonVO vo) {
        int total = personService.count(vo);
        Sort sort = Sort.by(Sort.Direction.DESC, "id");
        Page<Person> personPage = personService.fetch(vo, sort);
        logger.info(personPage.hasContent() + "");
        List<PersonVO> vos = personPage.getContent().stream().map(p -> new PersonVO(p)).collect(Collectors.toList());
        return Result.success(new PageData(vo.getPage(), vo.getSize(), total, vos));
    }

    @GetMapping("/{id}")
    public Person info(@PathVariable Long id) {
        Person person = personService.info(id);
        person.getName();
        return personService.info(id);
    }

    @PostMapping
    public Person add(@RequestBody PersonVO vo) {
        return personService.add(vo);
    }

    @PutMapping("/{id}")
    public Person edit(@RequestBody PersonVO vo, @PathVariable Long id) {
        return personService.edit(id, vo);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        personService.logicDelete(id);
    }

}
