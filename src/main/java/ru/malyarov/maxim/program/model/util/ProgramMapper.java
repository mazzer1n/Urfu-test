package ru.malyarov.maxim.program.model.util;

import lombok.experimental.UtilityClass;
import ru.malyarov.maxim.program.model.Program;
import ru.malyarov.maxim.program.model.dto.ProgramDto;

@UtilityClass
public class ProgramMapper {
    public static ProgramDto toProgramDto(Program program) {
        return ProgramDto.builder()
                .title(program.getTitle())
                .cypher(program.getCypher())
                .level(program.getLevel())
                .standard(program.getStandard())
                .instituteName(program.getInstitute().getTitle())
                .headName(program.getHead().getFullName())
                .accreditationTime(program.getAccreditationTime())
                .build();
    }

    public static Program toProgram(ProgramDto dto) {
        return Program.builder()
                .title(dto.getTitle())
                .cypher(dto.getCypher())
                .level(dto.getLevel())
                .standard(dto.getStandard())
                .accreditationTime(dto.getAccreditationTime())
                .build();
    }
}