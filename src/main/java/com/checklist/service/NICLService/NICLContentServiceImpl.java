package com.checklist.service.NICLService;

import com.checklist.model.Attribute;
import com.checklist.model.NICLContent;
import com.checklist.model.NICLHead;
import com.checklist.model.Template;
import com.checklist.repository.NICLContentRepository;
import com.checklist.repository.NICLHeadRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by azhang on 24/10/2017.
 */
@Service("NICLContentService")
@Transactional
public class NICLContentServiceImpl implements NICLContentService{
    @Autowired
    private NICLContentRepository niclContentRepository;
    @Autowired
    private NICLHeadService niclHeadService;

    @Override
    public void saveNICLContent(NICLContent niclContent) {

        NICLContent toSave = new NICLContent(niclContent.getValue(),niclContent.getAttribute());
        niclContentRepository.save(toSave);
        NICLHead head = niclContent.getNiclHead();
        toSave.setNiclHead(head);

    }

    @Override
    public Set<NICLContent> findAllNICLContentByHeadID(Long niclHeadID) {
        return niclHeadService.findNICLHeadByID(niclHeadID).getNICLContents();

    }
    @Override
    public Set<NICLContent> findAllNICLContentWithoutValueByHeadID(Long niclHeadID) {
        Set<NICLContent> result= new HashSet<NICLContent>();
        NICLHead head = niclHeadService.findNICLHeadByID(niclHeadID);
        Template template = niclHeadService.findNICLHeadByID(niclHeadID).getTemplate();
        NICLContent toSave;

        for(Attribute attri: template.getAttributes()){
            toSave = new NICLContent();
            toSave.setNiclHead(head);
            toSave.setAttribute(attri);
            result.add(toSave);
        }
        return result;

    }

    @Override
    public void saveNICLContentList(Set<NICLContent> niclContentSet) {
        for(NICLContent temp: niclContentSet){
            saveNICLContent(temp);
        }
    }
}
