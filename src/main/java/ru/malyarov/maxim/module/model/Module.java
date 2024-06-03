package ru.malyarov.maxim.module.model;

import lombok.*;
import ru.malyarov.maxim.data.EducationStandard;
import ru.malyarov.maxim.program.model.Program;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Module")
public class Module {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private UUID id;

    private String title;


    private String type;

    @ManyToMany(mappedBy = "modules")
    private List<Program> programs;

}
