package com.checklist.service.NICLService;

import com.checklist.model.NICLContent;
import com.checklist.model.NICLHead;

import java.util.List;
import java.util.Set;

/**
 * Created by azhang on 24/10/2017.
 */
public interface NICLContentService {
    void saveNICLContent(NICLContent niclContent);
    List<NICLContent> findAllNICLContentByHeadID(Long niclHeadID);
    Set<NICLContent> findAllNICLContentWithoutValueByHeadID(Long niclHeadID);

    void saveNICLContentList(Set<NICLContent> niclContentSet);
}
