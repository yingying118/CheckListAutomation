package com.checklist.service.NICLService;

import com.checklist.model.NICLHead;

import java.util.List;
import java.util.Set;

/**
 * Created by azhang on 16/10/2017.
 */
public interface NICLHeadService {
    NICLHead findNICLHeadByID(Long id);
    NICLHead saveNICLHead(NICLHead niclHead);
    List<NICLHead> findAllNICLHeads();
    NICLHead findNICLHeadByName(String name);

    Set<NICLHead> findAllNICLHeadsByGroupID(long gid);


}
