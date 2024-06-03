package ru.malyarov.maxim.module.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.malyarov.maxim.module.model.dto.ModuleDto;
import ru.malyarov.maxim.module.service.ModuleService;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/modules")
@Slf4j
public class ModuleController {
    private final ModuleService moduleService;

    @Autowired
    public ModuleController(ModuleService moduleService) {
        this.moduleService = moduleService;
    }

    @GetMapping
    public String getAll(Model model) {
        log.info("Fetching all modules");
        List<ModuleDto> modules = moduleService.findAll();
        model.addAttribute("modules", modules);
        log.info("Found {} modules", modules.size());
        return "modules/index";
    }

    @GetMapping("/new")
    public String showAddModuleForm(Model model) {
        model.addAttribute("moduleDto", new ModuleDto());
        return "modules/new";
    }

    @PostMapping("/new")
    public String addModule(@ModelAttribute @Valid ModuleDto moduleDto, BindingResult bindingResult) {
        log.info("Adding new module: {}", moduleDto);
        if (bindingResult.hasErrors())
            return "modules/new";

        moduleService.saveNewModule(moduleDto);
        log.info("Module added successfully");
        return "redirect:/modules";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") String title, Model model) {
        log.info("Fetching module with title: {}", title);
        ModuleDto moduleDto = moduleService.findByTitle(title);
        model.addAttribute("module", moduleDto);
        return "modules/show";
    }

    @GetMapping("/edit/{title}")
    public String edit(Model model, @PathVariable("title") String title) {
        log.info("Editing module with title: {}", title);
        model.addAttribute("module", moduleService.findByTitle(title));
        return "modules/edit";
    }

    @PatchMapping("/{title}")
    public String update(@ModelAttribute("module") @Valid ModuleDto module,
                         @PathVariable("title") String title, BindingResult bindingResult) {
        log.info("Updating module with id: {}", title);
        if (bindingResult.hasErrors())
            return "modules/edit";
        moduleService.update(title, module);
        log.info("Module updated successfully");
        return "redirect:/modules";
    }

    @DeleteMapping("/{title}")
    public String delete(@PathVariable("title") String title) {
        log.info("Deleting module with title: {}", title);
        moduleService.deleteByTitle(title);
        log.info("Module deleted successfully");
        return "redirect:/modules";
    }

}
