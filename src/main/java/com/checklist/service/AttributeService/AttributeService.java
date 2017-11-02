package com.checklist.service.AttributeService;

import com.checklist.model.Attribute;
import com.checklist.model.Group;

import java.util.List;

/**
 * Created by azhang on 10/10/2017.
 */
public interface AttributeService {
    Attribute findAttributeByID(Long id);
    void saveAttribute(Attribute attri);
    List<Attribute> findAllAttributes();
    boolean isAttributeExist(String name);
}
