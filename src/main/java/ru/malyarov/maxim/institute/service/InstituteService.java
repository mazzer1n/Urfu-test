package ru.malyarov.maxim.institute.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.malyarov.maxim.institute.model.Institute;
import ru.malyarov.maxim.institute.repository.InstituteRepository;

import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class InstituteService {
    private final InstituteRepository instituteRepository;

    @Autowired
    public InstituteService(InstituteRepository instituteRepository) {
        this.instituteRepository = instituteRepository;
    }

    public Optional<Institute> findByTitle(String title) {
        return instituteRepository.findByTitle(title);
    }

    @Transactional
    public void save(Institute institute) {
        instituteRepository.save(institute);
    }

    @Transactional
    public Institute saveByTitle(String title) {
        Institute institute = Institute.builder()
                .title(title)
                .build();

        return instituteRepository.save(institute);
    }

}
