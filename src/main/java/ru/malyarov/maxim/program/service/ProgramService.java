package ru.malyarov.maxim.program.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.malyarov.maxim.institute.model.Institute;
import ru.malyarov.maxim.institute.service.InstituteService;
import ru.malyarov.maxim.person.model.Person;
import ru.malyarov.maxim.person.service.PersonService;
import ru.malyarov.maxim.program.model.Program;
import ru.malyarov.maxim.program.model.dto.ProgramDto;
import ru.malyarov.maxim.program.model.util.ProgramMapper;
import ru.malyarov.maxim.program.repository.ProgramRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
public class ProgramService {
    private final ProgramRepository programRepository;
    private final InstituteService instituteService;
    private final PersonService personService;


    @Autowired
    public ProgramService(ProgramRepository programRepository, InstituteService instituteService, PersonService personService) {
        this.programRepository = programRepository;
        this.instituteService = instituteService;
        this.personService = personService;
    }

    public List<ProgramDto> findAll() {
        return programRepository.findAll().stream()
                .map(ProgramMapper::toProgramDto)
                .collect(Collectors.toList());
    }

    //Если института или лица еще нет в бд, то они добавляются вместе с программой
    @Transactional
    public void save(ProgramDto programDto) {
        Program program = ProgramMapper.toProgram(programDto);

        Institute institute = instituteService.findByTitle(programDto.getTitle())
                .orElseGet(() -> instituteService.saveByTitle(programDto.getTitle()));

        Person head = personService.findByFullName(programDto.getHeadName())
                .orElseGet(() -> personService.saveByFullName(programDto.getHeadName()));

        institute.setPrograms(new ArrayList<>());
        head.setPrograms(new ArrayList<>());

        program.setInstitute(institute);
        program.setHead(head);

        institute.getPrograms().add(program);
        head.getPrograms().add(program);

        programRepository.save(program);
    }


    @Transactional
    public void update(String cypher, ProgramDto updateProgramDto) {
        Program existingProgram = programRepository.findByCypher(cypher);
        Program updatedProgram = ProgramMapper.toProgram(updateProgramDto);

        Institute newInstitute = instituteService.findByTitle(updateProgramDto.getInstituteName())
                .orElseGet(() -> instituteService.saveByTitle(updateProgramDto.getInstituteName()));

        Person newHead = personService.findByFullName(updateProgramDto.getHeadName())
                .orElseGet(() -> personService.saveByFullName(updateProgramDto.getHeadName()));

        Institute oldInstitute = existingProgram.getInstitute();
        if (oldInstitute != null) {
            oldInstitute.getPrograms().remove(existingProgram);
            instituteService.save(oldInstitute);
        }

        Person oldHead = existingProgram.getHead();
        if (oldHead != null) {
            oldHead.getPrograms().remove(existingProgram);
            personService.save(oldHead);
        }

        existingProgram.setTitle(updatedProgram.getTitle());
        existingProgram.setCypher(updatedProgram.getCypher());
        existingProgram.setLevel(updatedProgram.getLevel());
        existingProgram.setStandard(updatedProgram.getStandard());
        existingProgram.setAccreditationTime(updatedProgram.getAccreditationTime());
        existingProgram.setInstitute(newInstitute);
        existingProgram.setHead(newHead);
        newInstitute.setPrograms(new ArrayList<>());
        newHead.setPrograms(new ArrayList<>());
        newInstitute.getPrograms().add(existingProgram);
        instituteService.save(newInstitute);

        newHead.getPrograms().add(existingProgram);
        personService.save(newHead);

        programRepository.save(existingProgram);
    }


    @Transactional
    public void deleteByCypher(String cypher) {
        programRepository.deleteByCypher(cypher);
    }


    public ProgramDto findByCypher(String cypher) {
        return ProgramMapper.toProgramDto(programRepository.findByCypher(cypher));
    }
}
