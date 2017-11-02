package com.checklist.service.AttributeValueService;

import com.checklist.model.AttributeValue;

import java.util.List;

/**
 * Created by azhang on 30/10/2017.
 */
public interface AttributeValueService {
    void saveAttributeValue(AttributeValue attributeValue);
    List<AttributeValue> findListByAttributeId(Long aid);
    List<AttributeValue> findListByAttributeName(String name);
    boolean hasAttributeValue(Long aid);
}
