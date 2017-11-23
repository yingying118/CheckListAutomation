package com.checklist.service.SectionAttributeService;

import com.checklist.model.Attribute;
import com.checklist.model.Section;
import com.checklist.model.Template;

import java.util.List;
import java.util.Set;

/**
 * Created by azhang on 20/11/2017.
 */
public interface SectionAttributeService {
    void saveSectionAttribute(Section section, Template template, Attribute attribute, int order);
    void saveStaticSectionAttribute(Section section,Template template);
    void saveStaticSectionAttribute(Section section, Template template, List<Attribute> attributeList);
}
