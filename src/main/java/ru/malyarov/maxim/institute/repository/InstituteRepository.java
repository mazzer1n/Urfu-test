package ru.malyarov.maxim.institute.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.malyarov.maxim.institute.model.Institute;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface InstituteRepository extends JpaRepository<Institute, UUID> {

    Optional<Institute> findByTitle(String title);

    @Query("SELECT CASE WHEN COUNT(i) > 0 THEN true ELSE false END FROM Institute i WHERE i.title = :title")
    boolean existsByTitle(@Param("title") String title);

}
