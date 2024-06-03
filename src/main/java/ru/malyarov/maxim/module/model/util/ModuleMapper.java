package ru.malyarov.maxim.module.model.util;

import ru.malyarov.maxim.module.model.Module;
import ru.malyarov.maxim.module.model.dto.ModuleDto;
import ru.malyarov.maxim.program.model.util.ProgramMapper;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class ModuleMapper {
    public static ModuleDto toModuleDto(Module module) {
        ModuleDto moduleDto = ModuleDto.builder()
                .title(module.getTitle() != null ? module.getTitle().replaceAll(" ", "-") : null)
                .type(module.getType())
                .programs(module.getPrograms() != null ?
                        module.getPrograms().stream()
                                .map(ProgramMapper::toProgramDto)
                                .collect(Collectors.toList()) : null) // Преобразование списка программ в список DTO
                .build();

        return moduleDto;
    }



    public static Module toModule(ModuleDto moduleDto) {
        Module module = new Module();
        module.setTitle(moduleDto.getTitle().replaceAll("-", " "));
        module.setType(moduleDto.getType());
        module.setPrograms(new ArrayList<>());
        return module;
    }
}

