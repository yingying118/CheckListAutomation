package com.checklist.service.AttributeValueService;

import com.checklist.model.AttributeValue;

import java.util.List;
import java.util.Set;

/**
 * Created by azhang on 30/10/2017.
 */
public interface AttributeValueService {
    void saveAttributeValue(AttributeValue attributeValue);
    Set<AttributeValue> findAttributeValueSetByAID(Long aid);
    boolean hasAttributeValue(Long aid);
    Set<AttributeValue> findAttributeValueSetByHeadID(Long hid);
}
