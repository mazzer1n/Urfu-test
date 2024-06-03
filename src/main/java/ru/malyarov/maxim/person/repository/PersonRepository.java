package ru.malyarov.maxim.person.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.malyarov.maxim.institute.model.Institute;
import ru.malyarov.maxim.person.model.Person;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface PersonRepository extends JpaRepository<Person, UUID> {
    Optional<Person> findByFullName(String fullName);

    @Query("SELECT CASE WHEN COUNT(p) > 0 THEN true ELSE false END FROM Person p WHERE p.fullName = :fullName")
    boolean existsByFullName(@Param("fullName") String fullName);

}
