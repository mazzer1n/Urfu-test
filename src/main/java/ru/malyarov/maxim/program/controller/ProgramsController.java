package ru.malyarov.maxim.program.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.malyarov.maxim.program.model.dto.ProgramDto;
import ru.malyarov.maxim.program.service.ProgramService;

import javax.validation.Valid;
import java.util.List;


@Controller
@RequestMapping("/programs")
@Slf4j
public class ProgramsController {
    private final ProgramService programService;

    @Autowired
    public ProgramsController(ProgramService programService) {
        this.programService = programService;
    }

    @GetMapping
    public String getAll(Model model) {
        log.info("Fetching all programs");
        List<ProgramDto> programs = programService.findAll();
        model.addAttribute("programs", programs);
        log.info("Found {} programs", programs.size());
        return "programs/index";
    }

    @GetMapping("/new")
    public String showAddProgramForm(Model model) {
        model.addAttribute("programDto", new ProgramDto());
        return "programs/new";
    }

    @PostMapping("/new")
    public String addProgram(@ModelAttribute @Valid ProgramDto programDto, BindingResult bindingResult) {
        log.info("Adding new program: {}", programDto);
        if (bindingResult.hasErrors())
            return "programs/new";

        programService.save(programDto);
        log.info("Program added successfully");
        return "redirect:/programs";
    }

    @GetMapping("/{cypher}")
    public String show(@PathVariable("cypher") String cypher, Model model) {
        log.info("Fetching program with cypher: {}", cypher);
        ProgramDto programDto = programService.findByCypher(cypher);
        model.addAttribute("program", programDto);
        return "programs/show";
    }

    @GetMapping("/edit/{cypher}")
    public String edit(Model model, @PathVariable("cypher") String cypher) {
        model.addAttribute("program", programService.findByCypher(cypher));
        return "programs/edit";
    }

    @PatchMapping("/{cypher}")
    public String update(@ModelAttribute("program") @Valid ProgramDto program,
                         @PathVariable("cypher") String cypher, BindingResult bindingResult) {
        log.info("Updating program with cypher: {}", cypher);
        if (bindingResult.hasErrors())
            return "programs/edit";

        programService.update(cypher, program);
        log.info("Program updated successfully");
        return "redirect:/programs";
    }

    @DeleteMapping("/{cypher}")
    public String delete(@PathVariable("cypher") String cypher) {
        log.info("Deleting program with cypher: {}", cypher);
        programService.deleteByCypher(cypher);
        log.info("Program deleted successfully");
        return "redirect:/programs";
    }
}
