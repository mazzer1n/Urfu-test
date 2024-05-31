package ru.malyarov.maxim.module.model;

import jakarta.persistence.*;
import lombok.*;
import ru.malyarov.maxim.data.EducationStandard;

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
    private UUID id;

    private String title;

    @Enumerated(EnumType.STRING)
    private EducationStandard standard;
}
