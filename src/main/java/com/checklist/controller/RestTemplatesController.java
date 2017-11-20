package com.checklist.controller;

/**
 * Created by azhang on 11/10/2017.
 */

import com.checklist.model.Attribute;
import com.checklist.model.Group;
import com.checklist.model.Template;
import com.checklist.service.TemplateService.TemplateService;
import jdk.nashorn.internal.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/templates")
public class RestTemplatesController {
String base = "http://localhost:8080";

    @Autowired
    TemplateService templateService;

    @RequestMapping(value="/getallgroups", method = RequestMethod.GET)
    public List<Group> getGroups()  {
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject(base+"/groups/getallgroups",List.class);
    }
    @RequestMapping(value="/getallattributes", method = RequestMethod.GET)
    public List<Attribute> getAttributes()  {
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject(base+"/attributes/getallattributes",List.class);
    }
    @RequestMapping(value="/getgroupbyid/{gid}", method=RequestMethod.GET)
    public Group getGroupById(@PathVariable("gid") Long id)  {
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject(base+"/groups/getgroupbyid/" + id, Group.class);
    }
    @RequestMapping(value="/createtemplate", method = RequestMethod.POST)
    public void createTemplate(@RequestBody Template template) throws Exception {
        System.out.println(template);
        templateService.saveTemplate(template);
    }

    @RequestMapping(value="/getgrouptemplates/{gid}", method=RequestMethod.GET)
    public Set<Template> getGroupTemplates(@PathVariable("gid") Long id){
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject(base+"/groups/getgrouptemplates/" + id, Set.class);
    }




}
