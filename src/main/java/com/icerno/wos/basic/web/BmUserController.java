package com.icerno.wos.basic.web;

import com.icerno.frame.exception.WosException;
import com.icerno.frame.model.PageData;
import com.icerno.frame.web.BaseController;
import com.icerno.wos.basic.service.BmUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/bmUser")
public class BmUserController extends BaseController {
    @Autowired
    private BmUserService bmUserService;

    @RequestMapping("/findForPaging")
    @ResponseBody
    public Map<String, Object> findAllBmUserForPaging() {
        try {
            PageData pd = getPageDate();
            List<PageData> list = bmUserService.findAllBmUserForPaging(pd);
            return this.retMsg(true, "获取用户列表成功", list);
        } catch (WosException e) {
            return this.retMsg(false, e.getMessage());
        }
    }
}
