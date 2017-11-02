package com.checklist.repository;

import com.checklist.model.NICLHead;
import com.checklist.model.Template;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by azhang on 16/10/2017.
 */
@Repository
public interface NICLHeadRepository extends JpaRepository<NICLHead, Long> {
}
