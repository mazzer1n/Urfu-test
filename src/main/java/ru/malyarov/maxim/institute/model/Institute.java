package ru.malyarov.maxim.institute.model;


import lombok.*;
import org.hibernate.annotations.Cascade;
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
@Table(name = "Institute")
public class Institute {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private UUID id;

    private String title;

    @OneToMany(mappedBy = "institute")
    @Cascade(org.hibernate.annotations.CascadeType.SAVE_UPDATE)
    private List<Program> programs;
}
