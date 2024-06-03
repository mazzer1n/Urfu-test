package ru.malyarov.maxim.program.model;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;
import ru.malyarov.maxim.data.EducationLevel;
import ru.malyarov.maxim.data.EducationStandard;
import ru.malyarov.maxim.institute.model.Institute;
import ru.malyarov.maxim.person.model.Person;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.UUID;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Program")
public class Program {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private UUID id;

    @Column(name = "title")
    private String title;

    //Шифр формата 09.04.03-33.03, иначе он портит эндпоинт
    @Column(name = "cypher")
    private String cypher;

    @Enumerated(EnumType.STRING)
    private EducationLevel level;

    @Enumerated(EnumType.STRING)
    private EducationStandard standard;

    @ManyToOne
    private Institute institute;

    @ManyToOne
    @JoinColumn(name = "person_id", referencedColumnName = "id")
    private Person head;

    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd") //2025-03-14
    private Date accreditationTime;

}
