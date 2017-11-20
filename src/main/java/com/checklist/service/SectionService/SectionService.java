package com.checklist.service.SectionService;

import com.checklist.model.Section;
import com.checklist.repository.SectionRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Set;

/**
 * Created by azhang on 16/11/2017.
 */
public interface SectionService {


    Section saveSection(Section section) throws Exception;
    Set<Section> findSectionbyTID(Long tid);
    Section findSectionBySID(Long sid);
    boolean isSectionNameInSameTemplateExist(Long tid, String sectionName);
}
