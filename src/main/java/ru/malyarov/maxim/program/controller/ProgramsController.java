package ru.malyarov.maxim.program.controller;

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
public class ProgramsController {
    private final ProgramService programService;

    @Autowired
    public ProgramsController(ProgramService programService) {
        this.programService = programService;
    }

    @GetMapping
    public String getAll(Model model) {
        List<ProgramDto> programs = programService.findAll();
        model.addAttribute("programs", programs);
        return "programs/index";
    }

    @GetMapping("/new")
    public String showAddProgramForm(Model model) {
        model.addAttribute("programDto", new ProgramDto());
        return "programs/new";
    }

    @PostMapping("/new")
    public String addProgram(@ModelAttribute @Valid ProgramDto programDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return "programs/new";

        programService.save(programDto);
        return "redirect:/programs";
    }

    @GetMapping("/{cypher}")
    public String show(@PathVariable("cypher") String cypher, Model model) {
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
        if (bindingResult.hasErrors())
            return "programs/edit";

        programService.update(cypher, program);
        return "redirect:/programs";
    }

    @DeleteMapping("/{cypher}")
    public String delete(@PathVariable("cypher") String cypher) {
        programService.deleteByCypher(cypher);
        return "redirect:/programs";
    }
}
