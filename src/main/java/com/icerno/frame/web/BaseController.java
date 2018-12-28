package com.icerno.frame.web;

import com.icerno.frame.model.PageData;
import com.icerno.frame.util.PubConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

public class BaseController {
    protected Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * 获取HttpServletRequest对象
     *
     * @return HttpServletRequest对象
     */
    public HttpServletRequest getRequest() {
        return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
    }

    /**
     * 获取请求数据
     *
     * @return 请求数据
     */
    public PageData getPageDate() {
        return new PageData(this.getRequest());
    }

    /**
     * 设置返回信息
     *
     * @param flag 传入标示; 成功 true, 失败 false;
     * @param message 标识信息
     * @return 返回信息
     */
    public Map<String, Object> retMsg(Boolean flag, String message) {
        Map<String, Object> map = new HashMap<>();
        map.put("result", flag);
        map.put("message", message);
        this.getRequest().setAttribute(PubConstants.REQUEST_ATTRIBUTE_KEY_CONTROLLER_RETURN_VALUE, map);
        return map;
    }

    public Map<String, Object> retMsg(Boolean flag, String message, Object obj) {
        Map<String, Object> map = new HashMap<>();
        map.put("result", flag);
        map.put("message", message);
        map.put("data", obj);
        this.getRequest().setAttribute(PubConstants.REQUEST_ATTRIBUTE_KEY_CONTROLLER_RETURN_VALUE, map);
        return map;
    }
}
