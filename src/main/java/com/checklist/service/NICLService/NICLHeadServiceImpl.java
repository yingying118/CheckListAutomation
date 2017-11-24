package com.checklist.service.NICLService;

import com.checklist.model.NICLContent;
import com.checklist.model.NICLHead;
import com.checklist.model.Section;
import com.checklist.model.Template;
import com.checklist.repository.GroupRepository;
import com.checklist.repository.NICLHeadRepository;
import com.checklist.service.AttributeValueService.AttributeValueService;
import com.checklist.service.NICLService.NICLHeadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by azhang on 16/10/2017.
 */
@Service("NICLHeadService")
@Transactional
public class NICLHeadServiceImpl implements NICLHeadService {

    @Autowired
    private NICLHeadRepository headRepository;
    @Autowired
    private GroupRepository groupRepository;

    @Override
    public NICLHead findNICLHeadByID(Long id) {

        return headRepository.findOne(id);
    }

    @Override
    public NICLHead saveNICLHead(NICLHead niclHead) {
        NICLHead saved = headRepository.save( new NICLHead(niclHead.getOwner(), niclHead.getName(),"not approved"));
        saved.setTemplate(niclHead.getTemplate());
        return saved;
    }

    @Override
    public List<NICLHead> findAllNICLHeads() {
        return headRepository.findAll();
    }

    @Override
    public NICLHead findNICLHeadByName(String name) {
        for(NICLHead head: headRepository.findAll()){
            if(head.getName().equalsIgnoreCase(name)){
                return head;
            }
        }
        return null;
    }

    @Override
    public void deleteNICLHeadByID(Long id) {
         headRepository.delete(id);
    }

    @Override
    public Set<NICLHead> findAllNICLHeadsByGroupID(long gid) {
        //get tempates
        Set<Template> templates =  groupRepository.getOne(gid).getTemplates();
        if(!templates.isEmpty()){
            Set<NICLHead> NICLHeadSet = new HashSet<NICLHead>();
            for(Template template: templates){
               NICLHeadSet.addAll(template.getNICLHeads());
            }
            return NICLHeadSet;
        }

        return null;
    }

    @Override
    public Template findTemplateByHID(Long hid) {
        return headRepository.findOne(hid).getTemplate();
    }
}
