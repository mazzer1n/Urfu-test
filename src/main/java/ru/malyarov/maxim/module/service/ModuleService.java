package ru.malyarov.maxim.module.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.malyarov.maxim.module.model.Module;
import ru.malyarov.maxim.module.model.dto.ModuleDto;
import ru.malyarov.maxim.module.model.util.ModuleMapper;
import ru.malyarov.maxim.module.repository.ModuleRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
public class ModuleService {

    private final ModuleRepository moduleRepository;

    @Autowired
    public ModuleService(ModuleRepository moduleRepository) {
        this.moduleRepository = moduleRepository;
    }

    public List<ModuleDto> findAll() {
        return moduleRepository.findAll().stream()
                .map(ModuleMapper::toModuleDto)
                .collect(Collectors.toList());
    }

    @Transactional
    public void saveNewModule(ModuleDto moduleDto) {
        Module module = ModuleMapper.toModule(moduleDto);
        System.out.println("сюда доходит " + module.toString());
        moduleRepository.save(module);
    }


    public ModuleDto findByTitle(String title) {
        return ModuleMapper.toModuleDto(moduleRepository.findByTitle(title));
    }

    //Из-за использования thymeleaf, а также UUID,
    //Приходится прибегать к более доступным ключевым словам для пользователя
    //Из-за этого в dto берется title (Который стал уникальным из-за костылей) как временный идентификатор
    //Выглядит как полный мазохизм, но заставлять пользователя вводить два uuid
    //Для связки Program и Module ----
    //Еще больший мазохизм
    //В JSON было бы проще думаю
    @Transactional
    public void update(String titleWithoutSpaces, ModuleDto moduleUdpate) {
        String title = replaceSpacesInString(titleWithoutSpaces);
        Module module = moduleRepository.findByTitle(title);
        module.setType(moduleUdpate.getType());
        moduleRepository.save(module);
    }

    @Transactional
    public void deleteByTitle(String titleWithoutSpaces) {
        String title = replaceSpacesInString(titleWithoutSpaces);
        moduleRepository.deleteByTitle(title);
    }
    //Это сделано для того, что когда мы переходим по DTO используя title, title оптимизируется для url
    public String replaceSpacesInString(String input) {
        return input.replaceAll(" ", "-");
    }
}
