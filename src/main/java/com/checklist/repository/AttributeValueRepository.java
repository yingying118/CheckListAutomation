package com.checklist.repository;

import com.checklist.model.AttributeValue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by azhang on 30/10/2017.
 */
@Repository
public interface AttributeValueRepository extends JpaRepository<AttributeValue, Long> {
}
