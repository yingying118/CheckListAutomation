package com.checklist.service.SectionAttributeService;

import com.checklist.model.Attribute;
import com.checklist.model.Section;
import com.checklist.model.SectionAttribute;
import com.checklist.model.Template;
import com.checklist.service.AttributeService.AttributeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.naming.directory.AttributeInUseException;
import java.util.List;
import java.util.Set;

/**
 * Created by azhang on 20/11/2017.
 */
@Service("sectionAttributeService")
public class SectionAttributeServiceImpl implements SectionAttributeService {
    @Autowired
    AttributeService attributeService;

    @Override
    public void saveSectionAttribute(Section section, Template template, Attribute attribute, int order) {
        SectionAttribute sectionAttribute = new SectionAttribute();
        sectionAttribute.setAttribute(attribute);
        sectionAttribute.setTemplate(template);
        sectionAttribute.setSection(section);
        sectionAttribute.setOrder(order);
        attribute.getSectionAttributes().add(sectionAttribute);
    }

    @Override
    public void saveStaticSectionAttribute(Section section, Template template) {

        for(Attribute attr: attributeService.findAllStaticAttibutes()){
            saveSectionAttribute(section,template,attr,100);
        }


    }

    @Override
    public void saveStaticSectionAttribute(Section section, Template template, List<Attribute> attributeList) {
        int order = 100;
        for(Attribute attr: attributeList){
            saveSectionAttribute(section,template,attr,order++);
        }
    }


}
