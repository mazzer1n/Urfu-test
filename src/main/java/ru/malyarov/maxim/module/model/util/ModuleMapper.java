package ru.malyarov.maxim.module.model.util;

import ru.malyarov.maxim.module.model.Module;
import ru.malyarov.maxim.module.model.dto.ModuleDto;

public class ModuleMapper {
    public static ModuleDto toModuleDto(Module module) {
        return ModuleDto.builder()
                .title(module.getTitle().replaceAll(" ", "-"))
                .type(module.getType())
                .programs(module.getPrograms())
                .build();
    }

    public static Module toModule(ModuleDto moduleDto) {
        Module module = new Module();
        module.setTitle(moduleDto.getTitle().replaceAll("-", " "));
        module.setType(moduleDto.getType());
        return module;
    }
}

