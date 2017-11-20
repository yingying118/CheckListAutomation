package com.checklist.service.SectionAttributeService;

import com.checklist.model.Attribute;
import com.checklist.model.Section;
import com.checklist.model.Template;

/**
 * Created by azhang on 20/11/2017.
 */
public interface SectionAttributeService {
    void saveSectionAttribute(Section section, Template template, Attribute attribute, int order);
}
