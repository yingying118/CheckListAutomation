package com.checklist.service.TemplateService;

import com.checklist.model.Attribute;
import com.checklist.model.Section;
import com.checklist.model.SectionAttribute;
import com.checklist.model.Template;
import com.checklist.repository.AttributeRepository;
import com.checklist.repository.SectionRepository;
import com.checklist.repository.TemplateRepository;
import com.checklist.service.SectionService.SectionService;
import com.checklist.service.TemplateService.TemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by azhang on 11/10/2017.
 */
@Service("templateService")
@Transactional
public class TemplateServiceImp implements TemplateService {

    @Autowired
    private TemplateRepository templateRepository;
    @Autowired
    private SectionService sectionService;
    @Autowired
    private SectionRepository sectionRepository;
    @Autowired
    private AttributeRepository attributeRepository;
    @Override
    public Template findTemplateByID(Long id) {
        return templateRepository.findOne(id);
    }

    @Override
    public Template saveTemplate(Template template) throws Exception {

        Template temp = templateRepository.save(new Template(template.getName(), template.getGroupObject()));
        if (template.getSections().isEmpty()) {
            throw new NullPointerException("template doesn't contain section information");
        }
        else {
            for (Section section : template.getSections()) {
                if(isSectionNameExist(temp.getId(),section.getName())){
                    throw new Exception("Duplicated Section Name!!!");
                }else{
                    Section savedSection = sectionService.saveSection(section);
                    temp.getSections().add(savedSection);
                    savedSection.setTemplate(temp);
                    for(SectionAttribute sectionAttribute : section.getSectionAttributes()){
                        sectionAttribute.setTemplate(temp);
                        sectionAttribute.setSection(savedSection);
                        savedSection.getSectionAttributes().add(sectionAttribute);
                    }
                }

            }
        }
        return temp;

    }

    @Override
    public List<Template> findAllTemplates() {
        return templateRepository.findAll();
    }

    @Override
    public boolean isSectionNameExist(Long tid, String name) {
        if(templateRepository.getOne(tid).getSections().isEmpty()){
            return false;
        }else{
            for(Section section: templateRepository.getOne(tid).getSections()){
                if(section.getName().equalsIgnoreCase(name)){
                    return true;
                }
            }return false;
        }

    }


}
