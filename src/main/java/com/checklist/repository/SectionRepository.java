package com.checklist.repository;

import com.checklist.model.Section;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by azhang on 16/11/2017.
 */
public interface SectionRepository extends JpaRepository<Section, Long> {
}
