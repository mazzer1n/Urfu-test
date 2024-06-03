package ru.malyarov.maxim.person.model.util;

import lombok.experimental.UtilityClass;
import ru.malyarov.maxim.person.model.Person;
import ru.malyarov.maxim.person.model.dto.PersonDto;
import ru.malyarov.maxim.program.model.util.ProgramMapper;

import java.util.stream.Collectors;

@UtilityClass
public class PersonMapper {
    public static PersonDto toPersonDto(Person person) {
        return PersonDto.builder()
                .fullName(person.getFullName())
                .build();
    }

    public static Person toPerson(PersonDto personDto) {
        Person person = new Person();
        person.setFullName(personDto.getFullName());
        if (personDto.getPrograms() != null) {
            person.setPrograms(personDto.getPrograms().stream()
                    .map(ProgramMapper::toProgram)
                    .collect(Collectors.toList()));
        }
        return person;
    }
}
