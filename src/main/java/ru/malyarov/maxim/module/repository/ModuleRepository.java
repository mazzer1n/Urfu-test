package ru.malyarov.maxim.module.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.malyarov.maxim.module.model.Module;

import java.util.UUID;

@Repository
public interface ModuleRepository extends JpaRepository<Module, UUID> {
}
