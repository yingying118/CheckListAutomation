package com.checklist.repository;

import com.checklist.model.Group;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by azhang on 06/10/2017.
 */
@Repository
public interface GroupRepository extends JpaRepository<Group, Long> {

}
