package com.checklist.service.SectionService;

import com.checklist.model.Section;
import com.checklist.model.Template;
import com.checklist.repository.SectionRepository;
import com.checklist.repository.TemplateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.naming.NameAlreadyBoundException;
import java.util.Set;

/**
 * Created by azhang on 16/11/2017.
 */
@Service("sectionService")
public class SectionServiceImp implements SectionService {

    @Autowired
    private SectionRepository sectionRepository;
    @Autowired
    private TemplateRepository templateRepository;
    @Override
    public Section saveSection(Section section) throws Exception {
        Section toSave = new Section(section.getName(),section.getSection_order());
        return sectionRepository.save(toSave);
    }

    @Override
    public Set<Section> findSectionbyTID(Long tid) {
        return templateRepository.getOne(tid).getSections();
    }

    @Override
    public Section findSectionBySID(Long sid) {
        return sectionRepository.getOne(sid);
    }

    @Override
    public boolean isSectionNameInSameTemplateExist(Long tid, String sectionName) {
        if(templateRepository.getOne(tid)==null){
            throw new NullPointerException("template with tid: " + tid + "doesn't exsit in database");
        }
        else{
            Template template = templateRepository.getOne(tid);
            if(template.getSections()!=null) {
                for (Section temp : template.getSections()) {
                    if (temp.getName().equalsIgnoreCase(sectionName)) return true;
                }
            }
            return false;
        }
    }

}

