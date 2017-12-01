package com.checklist.service.AttributeService;

import com.checklist.model.Attribute;
import com.checklist.model.AttributeValue;
import com.checklist.repository.AttributeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by azhang on 10/10/2017.
 */
@Service("attributeService")
@Transactional
public class AttributeServiceImp implements AttributeService {
    @Autowired
    private AttributeRepository attributeRepository;

    @Override
    public Attribute findAttributeByID(Long id) {
        return attributeRepository.getOne(id);
    }

    @Override
    public Attribute saveTextAttribute(Attribute attri) {
        Attribute newAttr = new Attribute(attri.getName());
        newAttr.setDescription(attri.getDescription());
        newAttr.setType("text");
        return attributeRepository.save(newAttr);
    }
    @Override
    public Attribute saveDropdownAttribute(Attribute attri) {
        Attribute newAttr = new Attribute(attri.getName());
        newAttr.setDescription(attri.getDescription());
        newAttr.setType("dropdown");
        Set<AttributeValue> avs= new HashSet<AttributeValue>();
        for(AttributeValue av: attri.getAttributeValues()){
            if(!av.getValue().isEmpty()){
                avs.add(new AttributeValue(av.getValue(),newAttr));
            }
        }
        newAttr.setAttributeValues(avs);
        return attributeRepository.save(newAttr);
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

    @Override
    public Attribute findStaticAttrByName(String name) throws Exception {
        for(Attribute attr: findAllStaticAttibutes())
           if(attr.getName().equalsIgnoreCase(name)){
            return attr;
           }
           throw new Exception("there is no static attribute in the database named: " + name);
    }

}
