package com.checklist.controller;

import com.checklist.model.*;
import com.checklist.service.GroupService.GroupService;
import com.checklist.service.NICLService.NICLContentService;
import com.checklist.service.NICLService.NICLHeadService;
import com.checklist.service.TemplateService.TemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Set;

/**
 * Created by azhang on 17/10/2017.
 */
@RestController
@RequestMapping("/NICL")
public class RestNICLController {


    @Autowired
    TemplateService templateService;
    @Autowired
    GroupService groupService;
    @Autowired
    NICLHeadService niclHeadService;
    @Autowired
    NICLContentService niclContentService;
    /**---------------------CRUD funcs----------------------**/
    @RequestMapping(value="/createniclhead", method = RequestMethod.POST)
    public void createNICLHead(@RequestBody NICLHead niclHead){
        niclHeadService.saveNICLHead(niclHead);
    }
    @RequestMapping(value="/createniclcontent",method = RequestMethod.POST)
    public void createNICLHead(@RequestBody NICLContent niclContent){
        niclContentService.saveNICLContent(niclContent);
    }

    @RequestMapping(value="/createniclcontentset",method = RequestMethod.POST)
    public void createNICLHead(@RequestBody Set<NICLContent> niclContentSet){
        niclContentService.saveNICLContentList(niclContentSet);
    }

    /**---------------------get funcs----------------------**/
    @RequestMapping(value="/getallgroups", method = RequestMethod.GET)
    public List<Group> getGroups()  {
        //System.out.println("hello");
        return groupService.findAllGroups();
    }

    @RequestMapping(value="/getgroupbyid/{gid}", method=RequestMethod.GET)
    public Group getGroupById(@PathVariable("gid") Long id)  {
        return groupService.findGroupByID(id);
    }


    @RequestMapping(value="/getgrouptemplates/{gid}", method=RequestMethod.GET)
    public Set<Template> getGroupTemplates(@PathVariable("gid") Long id){
        return groupService.findGroupByID(id).getTemplates();
    }
    @RequestMapping(value="/getniclheadbygroupid/{gid}", method=RequestMethod.GET)
    public Set<NICLHead> getNICLHeadByGroupID(@PathVariable("gid") long id){
           Set<NICLHead> niclHead = niclHeadService.findAllNICLHeadsByGroupID(id);
           return niclHead;
    }
    @RequestMapping(value="/gettemplateattributes/{tid}", method=RequestMethod.GET)
    public Set<Attribute> getTemplateAttributes(@PathVariable("tid") Long id){
        return templateService.findTemplateByID(id).getAttributes();
    }
    @RequestMapping(value="/gettemplatebyid/{tid}", method=RequestMethod.GET)
    public Template getTemplateByID(@PathVariable("tid") Long id){
        return templateService.findTemplateByID(id);
    }

    @RequestMapping(value="/gettemplatename/{tid}", method=RequestMethod.GET)
    public String getTemplateName(@PathVariable("tid") Long id){
        return templateService.findTemplateByID(id).getName();
    }
    @RequestMapping(value="/getniclcontentbyhid/{hid}", method=RequestMethod.GET)
    public List<NICLContent> getNICLContentSet(@PathVariable("hid") Long id){
        return niclContentService.findAllNICLContentByHeadID(id);
    }


}
