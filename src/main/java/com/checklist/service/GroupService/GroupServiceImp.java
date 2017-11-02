package com.checklist.service.GroupService;

import com.checklist.model.Group;
import com.checklist.repository.GroupRepository;
import com.checklist.service.GroupService.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by azhang on 06/10/2017.
 */
@Service("groupService")
@Transactional
public class GroupServiceImp implements GroupService {
    @Autowired
    private GroupRepository groupRepository;
    @Override
    public Group findGroupByID(Long id) {
        return groupRepository.findOne(id);
    }

    @Override
    public void saveGroup(Group group) {
        Group newGroup = new Group(group.getName());
        groupRepository.save(newGroup);

    }

    @Override
    public List<Group> findAllGroups() {
        return groupRepository.findAll();
    }

    @Override
    public boolean isGroupExist(String name) {
        return false;
    }
}
