package com.checklist.service.TemplateService;

import com.checklist.model.Attribute;
import com.checklist.model.Template;
import com.checklist.repository.AttributeRepository;
import com.checklist.repository.TemplateRepository;
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
    private AttributeRepository attributeRepository;

    @Override
    public Template findTemplateByID(Long id) {
        return templateRepository.findOne(id);
    }

    @Override
    public Template saveTemplate(Template template) {
        Template temp = new Template(template.getName(),template.getGroupObject());
        templateRepository.save(temp);

        if(!template.getAttributes().isEmpty()){
            for(Attribute attri: template.getAttributes()){
                Attribute at = attributeRepository.findOne(attri.getId());
                at.getTemplates().add(temp);
                attributeRepository.save(at);
            }
        }
        return temp;
    }

    @Override
    public List<Template> findAllTemplates() {
        return templateRepository.findAll();
    }
}
