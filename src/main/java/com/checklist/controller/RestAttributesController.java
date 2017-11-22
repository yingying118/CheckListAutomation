package com.checklist.controller;

import com.checklist.model.Attribute;
import com.checklist.service.AttributeService.AttributeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by azhang on 10/10/2017.
 */
@RestController
@RequestMapping("/attributes")
public class RestAttributesController {
    @Autowired
    AttributeService attributeService;
    @RequestMapping(value="/createattribute", method = RequestMethod.POST)
    public void createProject(@RequestBody Attribute attr){
         attributeService.saveAttribute(attr);
    }
    @RequestMapping(value="/getallattributes", method = RequestMethod.GET)
    public List<Attribute> getAllAttributes()  {
        return attributeService.findAllAttributes();
    }
    @RequestMapping(value="/getnonstaticattributes", method = RequestMethod.GET)
    public List<Attribute> getNonStaticAttributes()  {
        return attributeService.findAllNonStaticAttributes();
    }

    @RequestMapping(value="/getstaticattributes", method = RequestMethod.GET)
    public List<Attribute> getStaticAttributes()  {
        return attributeService.findAllStaticAttibutes();
    }

}
