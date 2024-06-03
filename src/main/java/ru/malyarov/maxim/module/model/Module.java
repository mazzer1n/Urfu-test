package ru.malyarov.maxim.module.model;

import lombok.*;
import ru.malyarov.maxim.data.EducationStandard;

import javax.persistence.*;
import java.util.UUID;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "module")
public class Module {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private UUID id;

    private String title;

    @Enumerated(EnumType.STRING)
    private EducationStandard standard;
}
