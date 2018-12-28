package com.icerno.wos.basic.service;

import com.icerno.frame.exception.WosException;
import com.icerno.frame.model.PageData;
import com.icerno.frame.service.BaseService;
import com.icerno.wos.basic.dao.BmUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class BmUserService extends BaseService {

    @Autowired
    private BmUserMapper bmUserMapper;

    public List<PageData> findAllBmUserForPaging(PageData pd) throws WosException {
        List<PageData> list = null;
        try {
            list = bmUserMapper.findAllBmUserForPaging(pd);
        } catch (Exception e) {
            logger.error("获取用户数据出错\n" + e);
            throw new WosException("获取用户数据出错", e);
        }
        return list;
    }
}
