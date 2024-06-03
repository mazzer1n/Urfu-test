package ru.malyarov.maxim.program.model.dto;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;
import ru.malyarov.maxim.data.EducationLevel;
import ru.malyarov.maxim.data.EducationStandard;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Date;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProgramDto {

    @NotEmpty(message = "Name should not be empty")
    @Size(min = 2, max = 255, message = "Title should be between 2 and 255 characters")
    private String title;

    @NotEmpty(message = "Cypher should not be empty")
    @Pattern(regexp = "^\\d{2}\\.\\d{2}\\.\\d{2}-\\d{2}\\.\\d{2}$", message = "Invalid cypher format")
    private String cypher;

    @NotNull(message = "Level should not be empty")
    private EducationLevel level;

    @NotNull(message = "Standard should not be empty")
    private EducationStandard standard;

    @NotEmpty(message = "Institute name should not be empty")
    private String instituteName;

    @NotEmpty(message = "Head name should not be empty")
    private String headName;

    @NotNull(message = "Accreditation Time should not be empty")
    @DateTimeFormat(pattern = "yyyy-MM-dd") //2025-03-14
    private Date accreditationTime;
}
