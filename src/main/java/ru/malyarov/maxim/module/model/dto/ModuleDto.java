package ru.malyarov.maxim.module.model.dto;

import lombok.*;
import ru.malyarov.maxim.program.model.Program;

import javax.validation.constraints.NotEmpty;
import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ModuleDto {

    @NotEmpty
    private String title;

    @NotEmpty
    private String type;

    private List<Program> programs;
}
