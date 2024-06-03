package ru.malyarov.maxim.module.service;

import org.springframework.stereotype.Service;
import ru.malyarov.maxim.module.model.dto.ModuleDto;

import java.util.List;

@Service
public class ModuleService {
    public List<ModuleDto> findAll() {
        return null;
    }

    public void save(ModuleDto moduleDto) {
    }

    public ModuleDto findByTitle(String title) {
        return null;
    }

    public void update(String tittle, ModuleDto module) {
    }

    public void deleteByTitle(String title) {
    }

    public String replaceSpacesInString(String input) {
        return input.replaceAll(" ", "-");
    }
}
