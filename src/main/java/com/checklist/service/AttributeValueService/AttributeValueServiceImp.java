package com.checklist.service.AttributeValueService;

import com.checklist.model.Attribute;
import com.checklist.model.AttributeValue;
import com.checklist.repository.AttributeValueRepository;
import com.checklist.service.AttributeService.AttributeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by azhang on 30/10/2017.
 */
@Service("attributeValueService")
@Transactional
public class AttributeValueServiceImp implements AttributeValueService {
    @Autowired
    AttributeValueRepository attributeValueRepository;
    @Override
    public void saveAttributeValue(AttributeValue attributeValue) {
        AttributeValue temp = new AttributeValue(attributeValue.getValue(), attributeValue.getAttribute());
        attributeValueRepository.save(temp);

    }

    @Override
    public List<AttributeValue> findListByAttributeId(Long aid) {
        List<AttributeValue> attriValueList=new ArrayList<AttributeValue>();
        for(AttributeValue temp: attributeValueRepository.findAll()){
            if(temp.getAttribute().getId()==aid){
                attriValueList.add(temp);
            }
        }
        return attriValueList;

    }

    @Override
    public List<AttributeValue> findListByAttributeName(String name) {
        List<AttributeValue> attriValueList=new ArrayList<AttributeValue>();
        for(AttributeValue temp: attributeValueRepository.findAll()){
            if(temp.getAttribute().getName().equalsIgnoreCase(name)){
                attriValueList.add(temp);
            }
        }
        return attriValueList;
    }

    @Override
    public boolean hasAttributeValue(Long aid) {
        for(AttributeValue temp: attributeValueRepository.findAll()){
            if(temp.getAttribute().getId()==aid){
                return true;
            }
        }
        return false;
    }
}
