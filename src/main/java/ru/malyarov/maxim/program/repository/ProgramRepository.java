package ru.malyarov.maxim.program.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.malyarov.maxim.person.model.Person;
import ru.malyarov.maxim.program.model.Program;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface ProgramRepository extends JpaRepository<Program, UUID> {

    Program findByCypher(String cypher);

    void deleteByCypher(String cypher);


}
