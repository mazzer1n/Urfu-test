package ru.malyarov.maxim.person.model.dto;

import lombok.*;
import ru.malyarov.maxim.program.model.Program;
import ru.malyarov.maxim.program.model.dto.ProgramDto;

import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PersonDto {
    private String fullName;

    private List<ProgramDto> programs;
}
