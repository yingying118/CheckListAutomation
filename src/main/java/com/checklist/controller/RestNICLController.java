package com.checklist.controller;

import com.checklist.model.*;
import com.checklist.service.AttributeValueService.AttributeValueService;
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
    @Autowired
    AttributeValueService attributeValueService;
    /**---------------------CRUD funcs----------------------**/
    @RequestMapping(value="/createniclhead", method = RequestMethod.POST)
    public NICLHead createNICLHead(@RequestBody NICLHead niclHead){
        return niclHeadService.saveNICLHead(niclHead);
    }
    @RequestMapping(value="/createniclcontent",method = RequestMethod.POST)
    public void createNICLHead(@RequestBody NICLContent niclContent){
        niclContentService.saveNICLContent(niclContent);
    }

    @RequestMapping(value="/createniclcontentset",method = RequestMethod.POST)
    public void createContentSet(@RequestBody Set<NICLContent> niclContentSet){
        niclContentService.saveNICLContentList(niclContentSet);
    }

    /**---------------------get funcs----------------------**/
    @RequestMapping(value="/getallgroups", method = RequestMethod.GET)
    public List<Group> getGroups()  {

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

    @RequestMapping(value="/gettemplatebyid/{tid}", method=RequestMethod.GET)
    public Template getTemplateByID(@PathVariable("tid") Long id){
        return templateService.findTemplateByID(id);
    }
    @RequestMapping(value="/gettemplatebyhid/{hid}", method=RequestMethod.GET)
    public Template getTemplateByHID(@PathVariable("hid") Long hid){
        return niclHeadService.findTemplateByHID(hid);
    }
    @RequestMapping(value="/gettemplatename/{tid}", method=RequestMethod.GET)
    public String getTemplateName(@PathVariable("tid") Long id){
        return templateService.findTemplateByID(id).getName();
    }

    @RequestMapping(value="/getniclcontentwithoutvaluebyhid/{hid}", method=RequestMethod.GET)
    public Set<NICLContent> getNICLContentSetWithoutValueByHeadID(@PathVariable("hid") Long id){

        return niclContentService.findAllNICLContentWithoutValueByHeadID(id);

    }
    @RequestMapping(value="/getniclcontentbyhid/{hid}", method=RequestMethod.GET)
    public Set<NICLContent> getNICLContentSetByHeadID(@PathVariable("hid") Long id){
        return niclContentService.findAllNICLContentByHeadID(id);
    }

    @RequestMapping(value="/getattributevaluebyhid/{hid}", method=RequestMethod.GET)
    public Set<AttributeValue> getAttributeValueSetByHeadID(@PathVariable("hid") Long id){
        return attributeValueService.findAttributeValueSetByHeadID(id);
    }

    @RequestMapping(value="/getattributevaluebyaid/{aid}", method=RequestMethod.GET)
    public Set<AttributeValue> getAttributeValueSetByAttributeID(@PathVariable("aid") Long id){
        return attributeValueService.findAttributeValueSetByAID(id);
    }


    @RequestMapping(value="/deleteniclheadbyid/{hid}", method=RequestMethod.DELETE)
    public void deleteNICLHeadByID(@PathVariable("hid") Long id){
         niclHeadService.deleteNICLHeadByID(id);
    }


}
