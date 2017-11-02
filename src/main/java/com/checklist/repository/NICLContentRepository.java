package com.checklist.repository;

import com.checklist.model.NICLContent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by azhang on 24/10/2017.
 */
@Repository
public interface NICLContentRepository extends JpaRepository<NICLContent, Long> {
}
