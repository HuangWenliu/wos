package com.icerno.wos.basic.dao;

import com.icerno.frame.dao.BaseDao;
import com.icerno.frame.model.PageData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
@SuppressWarnings("unchecked")
public class BmUserMapper {
    @Autowired
    private BaseDao baseDao;

    public List<PageData> findAllBmUserForPaging(PageData pd) throws Exception {
        return (List<PageData>) baseDao.findForList("BmUserMapper.findAllBmUserForPaging", pd);
    }
}
