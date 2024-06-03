package ru.malyarov.maxim.person.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.malyarov.maxim.institute.model.Institute;
import ru.malyarov.maxim.institute.model.dto.InstituteDto;
import ru.malyarov.maxim.institute.model.util.InstituteMapper;
import ru.malyarov.maxim.person.model.Person;
import ru.malyarov.maxim.person.model.dto.PersonDto;
import ru.malyarov.maxim.person.model.util.PersonMapper;
import ru.malyarov.maxim.person.repository.PersonRepository;

import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class PersonService {
    private final PersonRepository personRepository;

    @Autowired
    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public Optional<Person> findByFullName(String fullName) {
        return personRepository.findByFullName(fullName);
    }


    @Transactional
    public Person saveByFullName(String fullName) {
        Person person = Person.builder()
                .fullName(fullName)
                .build();
        return personRepository.save(person);
    }

    @Transactional
    public void save(Person person) {
        personRepository.save(person);
    }
}
