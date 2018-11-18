package com.chen.dao.person;

import com.chen.dao.BaseRepository;
import com.chen.enums.PersonType;
import com.chen.model.person.Person;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepository extends BaseRepository<Person> {

    @Query("from Person where deleted = false and phone = ?1")
    Person findByPhone(String phone);

    @Query("from Person where deleted = false and type = ?1 and username = ?2")
    Person findByTypeAndUsername(PersonType type, String username);

}
