package com.checklist.service.AttributeService;

import com.checklist.model.Attribute;

import java.util.List;

/**
 * Created by azhang on 10/10/2017.
 */
public interface AttributeService {
    Attribute findAttributeByID(Long id);
    Attribute saveDropdownAttribute(Attribute dropdownAttribute);
    Attribute saveTextAttribute(Attribute textAttribute);

    List<Attribute> findAllAttributes();
    boolean isAttributeExist(String name);
    List<Attribute> findAllStaticAttibutes();
    List<Attribute> findAllNonStaticAttributes();
    Attribute findStaticAttrByName(String name) throws Exception;
}
