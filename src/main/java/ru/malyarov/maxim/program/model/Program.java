package ru.malyarov.maxim.program.model;

import jakarta.persistence.*;
import lombok.*;
import ru.malyarov.maxim.data.EducationLevel;
import ru.malyarov.maxim.data.EducationStandard;
import ru.malyarov.maxim.institute.model.Institute;
import ru.malyarov.maxim.person.model.Person;

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

    @Column(name = "cypher")
    private String cypher;

    @Enumerated(EnumType.STRING)
    private EducationLevel level;

    @Enumerated(EnumType.STRING)
    private EducationStandard standard;

    @ManyToOne
    @JoinColumn(name = "institute_id", referencedColumnName = "id")
    private Institute institute;

    @ManyToOne
    @JoinColumn(name = "person_id", referencedColumnName = "id")
    private Person head;

    // accreditationTime дата следующей аккредитации 2025-03-14

}
