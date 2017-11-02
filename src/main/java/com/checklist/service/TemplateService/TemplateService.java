package com.checklist.service.TemplateService;

import com.checklist.model.Attribute;
import com.checklist.model.Template;

import java.util.List;

/**
 * Created by azhang on 11/10/2017.
 */
public interface TemplateService {
    Template findTemplateByID(Long id);
    Template saveTemplate(Template template);
    List<Template> findAllTemplates();
}
