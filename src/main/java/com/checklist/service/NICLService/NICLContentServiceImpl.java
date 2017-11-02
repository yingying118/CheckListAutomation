package com.checklist.service.NICLService;

import com.checklist.model.NICLContent;
import com.checklist.model.NICLHead;
import com.checklist.repository.NICLContentRepository;
import com.checklist.repository.NICLHeadRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
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

        //NICLContent toSave = new NICLContent(niclContent.getValue(),niclContent.getAttributeID());
        NICLContent toSave = new NICLContent(niclContent.getValue(),niclContent.getAttribute());
        niclContentRepository.save(toSave);
        NICLHead head = niclHeadService.findNICLHeadByName(niclContent.getNiclHead().getName());
        toSave.setNiclHead(head);

    }

    @Override
    public List<NICLContent> findAllNICLContentByHeadID(Long niclHeadID) {
        List<NICLContent> searchResult= new ArrayList<NICLContent>();
        for(NICLContent content:niclContentRepository.findAll()){
            if(content.getNiclHead().getId()==niclHeadID){}
            searchResult.add(content);
        }
        return searchResult;
    }

    @Override
    public void saveNICLContentList(Set<NICLContent> niclContentSet) {
        for(NICLContent temp: niclContentSet){
            saveNICLContent(temp);
        }
    }
}
