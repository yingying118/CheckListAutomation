package com.checklist.service.AttributeValueService;

import com.checklist.model.Attribute;
import com.checklist.model.AttributeValue;
import com.checklist.repository.AttributeRepository;
import com.checklist.repository.AttributeValueRepository;
import com.checklist.repository.NICLHeadRepository;
import com.checklist.service.AttributeService.AttributeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by azhang on 30/10/2017.
 */
@Service("attributeValueService")
@Transactional
public class AttributeValueServiceImp implements AttributeValueService {
    @Autowired
    AttributeValueRepository attributeValueRepository;
    @Autowired
    AttributeRepository attributeRepository;
    @Autowired
    NICLHeadRepository niclHeadRepository;
    @Override
    public void saveAttributeValue(AttributeValue attributeValue) {
        AttributeValue temp = new AttributeValue(attributeValue.getValue(), attributeValue.getAttribute());
        attributeValueRepository.save(temp);

    }

    public void saveAttributeValueSet(Set<AttributeValue> attributeValuesSet){
        if(!attributeValuesSet.isEmpty()){
            for(AttributeValue attributevalue:attributeValuesSet){
                attributeValueRepository.save(attributevalue);
            }
        }
    }

    @Override
    public Set<AttributeValue> findAttributeValueSetByAID(Long aid) {
        return attributeRepository.getOne(aid).getAttributeValues();
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

    @Override
    public Set<AttributeValue> findAttributeValueSetByHeadID(Long hid) {
        return null;
    }


}
