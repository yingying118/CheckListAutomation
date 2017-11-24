package com.checklist.service.TemplateService;

import com.checklist.model.Attribute;
import com.checklist.model.Section;
import com.checklist.model.SectionAttribute;
import com.checklist.model.Template;
import com.checklist.repository.AttributeRepository;
import com.checklist.repository.SectionRepository;
import com.checklist.repository.TemplateRepository;
import com.checklist.service.AttributeService.AttributeService;
import com.checklist.service.SectionAttributeService.SectionAttributeService;
import com.checklist.service.SectionService.SectionService;
import com.checklist.service.TemplateService.TemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.w3c.dom.Attr;

import javax.transaction.Transactional;
import java.util.ArrayList;
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
    private SectionAttributeService sectionAttributeService;
    @Autowired
    private AttributeService attributeService;

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

            //save static finance_contact here:
            Section schedule= new Section("Schedule", 100,temp,true);
            sectionRepository.save(schedule);
            temp.getSections().add(schedule);

            List<Attribute> scheduleAttrs = new ArrayList<Attribute>(){{
                try{
                    add(attributeService.findStaticAttrByName("Prepayment Schedule"));
                    add(attributeService.findStaticAttrByName("Call Schedule"));
                }catch (Exception e){
                    throw e;
                }
            }};
            sectionAttributeService.saveStaticSectionAttribute(schedule,temp,scheduleAttrs);



            //save static finance_contact here:
            Section finance_contact= new Section("Finance Contact", 101,temp,true);
            sectionRepository.save(finance_contact);
            temp.getSections().add(finance_contact);

            List<Attribute> financeContactAttrs = new ArrayList<Attribute>(){{
                try{
                    add(attributeService.findStaticAttrByName("Agent Name"));
                    add(attributeService.findStaticAttrByName("Contact Name"));
                    add(attributeService.findStaticAttrByName("Telephone"));
                    add(attributeService.findStaticAttrByName("Email"));

                }catch (Exception e){
                    throw e;
                }
            }};
            sectionAttributeService.saveStaticSectionAttribute(finance_contact,temp,financeContactAttrs);


            //save static audit section here:
            Section audit= new Section("Audit", 102,temp,true);
            sectionRepository.save(audit);
            temp.getSections().add(audit);

            List<Attribute> auditAttr = new ArrayList<Attribute>(){{
                try{
                    add(attributeService.findStaticAttrByName("Prepared name"));
                    add(attributeService.findStaticAttrByName("Reviewed name"));
                    add(attributeService.findStaticAttrByName("Prepared date"));
                    add(attributeService.findStaticAttrByName("Reviewed date"));

                }catch (Exception e){
                    throw e;
                }
            }};
            sectionAttributeService.saveStaticSectionAttribute(audit, temp, auditAttr);

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
