package com.checklist.repository;

import com.checklist.model.Attribute;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by azhang on 10/10/2017.
 */
@Repository
public interface AttributeRepository extends JpaRepository<Attribute, Long> {
}
