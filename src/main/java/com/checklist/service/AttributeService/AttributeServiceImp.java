package com.checklist.service.AttributeService;

import com.checklist.model.Attribute;
import com.checklist.repository.AttributeRepository;
import com.checklist.repository.TemplateRepository;
import com.checklist.service.AttributeService.AttributeService;
import org.hibernate.annotations.Where;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by azhang on 10/10/2017.
 */
@Service("attributeService")
@Transactional
public class AttributeServiceImp implements AttributeService {
    @Autowired
    private AttributeRepository attributeRepository;
    @Autowired
    private TemplateRepository templateRepository;
    @Override
    public Attribute findAttributeByID(Long id) {
        return attributeRepository.getOne(id);
    }

    @Override
    public void saveAttribute(Attribute attri) {
        Attribute newAttr = new Attribute(attri.getName());
        attributeRepository.save(newAttr);
    }

    @Override
    public List<Attribute> findAllAttributes() {
       return attributeRepository.findAll();

    }


    @Override
    public boolean isAttributeExist(String name) {

        for(Attribute attr: attributeRepository.findAll()){
            if(attr.getName().equalsIgnoreCase(name)){
                return true;
            }
        }
        return false;
    }

    @Override
    public List<Attribute> findAllStaticAttibutes() {
        List<Attribute> staticAttributes = new ArrayList<>();
        for(Attribute attr: attributeRepository.findAll()){
            if(attr.isStatic()){
                staticAttributes.add(attr);
            }
        }
        return staticAttributes;
    }

    @Override
    public List<Attribute> findAllNonStaticAttributes() {
        List<Attribute> nonStaticAttributes = new ArrayList<>();
        for(Attribute attr: attributeRepository.findAll()){
            if(!attr.isStatic()){
                nonStaticAttributes.add(attr);
            }
        }
        return nonStaticAttributes;
    }

}
