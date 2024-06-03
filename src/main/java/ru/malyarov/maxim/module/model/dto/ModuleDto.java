package ru.malyarov.maxim.module.model.dto;

import lombok.*;
import ru.malyarov.maxim.program.model.dto.ProgramDto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ModuleDto {

    @NotEmpty(message = "Title should not be empty")
    @Size(min = 2, max = 255, message = "Title should be between 2 and 255 characters")
    private String title;

    @NotEmpty(message = "Title should not be empty")
    private String type;

    private List<ProgramDto> programs;
}
