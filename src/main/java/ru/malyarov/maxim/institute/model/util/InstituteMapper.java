package ru.malyarov.maxim.institute.model.util;

import lombok.experimental.UtilityClass;
import ru.malyarov.maxim.institute.model.Institute;
import ru.malyarov.maxim.institute.model.dto.InstituteDto;
import ru.malyarov.maxim.program.model.util.ProgramMapper;

import java.util.stream.Collectors;

@UtilityClass
public class InstituteMapper {

    public static InstituteDto toDto(Institute institute) {
        if (institute == null) {
            return null;
        }
        return InstituteDto.builder()
                .title(institute.getTitle())
                .build();
    }

    public static Institute toInstitute(InstituteDto instituteDto) {
        if (instituteDto == null) {
            return null;
        }
        Institute institute = new Institute();
        institute.setTitle(instituteDto.getTitle());
        if (instituteDto.getPrograms() != null) {
            institute.setPrograms(instituteDto.getPrograms().stream()
                    .map(ProgramMapper::toProgram)
                    .collect(Collectors.toList()));
        }
        return institute;
    }
}


