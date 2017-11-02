package com.checklist.service.GroupService;

import com.checklist.model.Group;

import java.util.List;
import java.util.Set;

/**
 * Created by azhang on 06/10/2017.
 */
public interface GroupService {
    Group findGroupByID(Long id);
    void saveGroup(Group group);
    List<Group> findAllGroups();
    boolean isGroupExist(String name);
}
