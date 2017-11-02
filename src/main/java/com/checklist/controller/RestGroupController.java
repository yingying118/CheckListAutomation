package com.checklist.controller;

import com.checklist.model.Group;
import com.checklist.model.Template;
import com.checklist.service.GroupService.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

/**
 * Created by azhang on 06/10/2017.
 */
@RestController
@RequestMapping("/groups")
public class RestGroupController {
    @Autowired
    GroupService groupService;
    @RequestMapping(value="/creategroup", method = RequestMethod.POST)
    public void createProject(@RequestBody Group group)  {
         groupService.saveGroup(group);
    }
    @RequestMapping(value="/getallgroups", method = RequestMethod.GET)
    public List<Group> getGroups()  {
        return groupService.findAllGroups();
    }
    @RequestMapping(value="/getgroupbyid/{gid}", method=RequestMethod.GET)
    public Group getGroupById(@PathVariable("gid") Long id) {
        return groupService.findGroupByID(id);
    }
    @RequestMapping(value="/getgrouptemplates/{gid}",method=RequestMethod.GET)
    public Set<Template> getGroupTemplate(@PathVariable("gid")Long id){
        return groupService.findGroupByID(id).getTemplates();
    }
}
