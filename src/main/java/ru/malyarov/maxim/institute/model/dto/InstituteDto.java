package ru.malyarov.maxim.institute.model.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.malyarov.maxim.program.model.dto.ProgramDto;

import java.util.List;

@Setter
@Getter
@Builder
public class InstituteDto {
    private String title;
    private List<ProgramDto> programs;
}
