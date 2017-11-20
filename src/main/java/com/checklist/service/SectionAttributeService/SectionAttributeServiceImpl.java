package com.checklist.service.SectionAttributeService;

import com.checklist.model.Attribute;
import com.checklist.model.Section;
import com.checklist.model.SectionAttribute;
import com.checklist.model.Template;

/**
 * Created by azhang on 20/11/2017.
 */
public class SectionAttributeServiceImpl implements SectionAttributeService {

    @Override
    public void saveSectionAttribute(Section section, Template template, Attribute attribute, int order) {
        SectionAttribute sectionAttribute = new SectionAttribute();
        sectionAttribute.setAttribute(attribute);
        sectionAttribute.setTemplate(template);
        sectionAttribute.setSection(section);
        sectionAttribute.setOrder(order);
        attribute.getSectionAttributes().add(sectionAttribute);
    }


}
