package ru.malyarov.maxim.person.model;

import javax.persistence.*;
import lombok.*;
import org.hibernate.annotations.Cascade;
import ru.malyarov.maxim.program.model.Program;

import java.util.List;
import java.util.UUID;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Person")
public class Person {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private UUID id;

    private String fullName;

    @OneToMany(mappedBy = "head")
    @Cascade(org.hibernate.annotations.CascadeType.SAVE_UPDATE)
    private List<Program> programs;

}
