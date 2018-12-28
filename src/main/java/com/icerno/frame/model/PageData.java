package com.icerno.frame.model;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * 转换类
 * 根据requset把字段转换成map
 *
 * @author HWL
 */
public class PageData extends HashMap<String, Object> implements Map<String, Object> {
    private static final long serialVersionUID = 1L;

    protected Logger logger = LoggerFactory.getLogger(this.getClass());

    Map<String, Object> map = null;
    HttpServletRequest request;

    public PageData(HttpServletRequest request) {
        this.request = request;
        Map<String, String[]> properties = request.getParameterMap();
        Map<String, Object> returnMap = new HashMap<>();
        Iterator<Entry<String, String[]>> entries = properties.entrySet().iterator();
        Map.Entry<String, String[]> entry;
        String name = "";
        String value = "";
        String operFun = "find";
        while (entries.hasNext()) {
            entry = entries.next();
            name = (String) entry.getKey();
            Object valueObj = entry.getValue();
            if (null == valueObj) {
                value = "";
            } else {
                String[] values = (String[]) valueObj;
                for (int i = 0; i < values.length; i++) {
                    value = values[i] + ",";
                }
                value = value.substring(0, value.length() - 1);
            }

            returnMap.put(name, value);
        }

//        HttpSession session = request.getSession();
//        Map<String, Object> userInfo = null;
//        if (session != null) {
//            userInfo = (Map<String, Object>) session.getAttribute("user");
//            if (userInfo != null) {
//                returnMap.put("cmp_id", userInfo.get("cmp_id"));
//            }
//        }
//
//        if (returnMap.containsKey("oper")) {
//            String operatType = returnMap.get("oper").toString();
//            operFun = operatType;
//            if (operatType != null && operatType.trim().length() > 0) {
//
//                if (operatType.equals("add")) {
//                    if (!returnMap.containsKey("id")) {
//                        returnMap.put("id", PubUtil.get32UUID());
//                    }
//                    if (returnMap.get("id") == "_empty" || returnMap.get("id").toString().trim().length() != 32) {
//                        returnMap.put("id", PubUtil.get32UUID());
//                    }
//
//                    if (userInfo != null) {
//                        returnMap.put("reg_user", userInfo.get("user_id"));
//                        returnMap.put("update_user", userInfo.get("user_id"));
//                    }
//                    returnMap.put("reg_date", PubUtil.getDateTime());
//                    returnMap.put("update_date", PubUtil.getDateTime());
//                    returnMap.put("autover", 0);
//                }
//                if (operatType.equals("edit")) {
//                    if (userInfo != null) {
//                        returnMap.put("update_user", userInfo.get("user_id"));
//                    }
//                    returnMap.put("update_date", PubUtil.getDateTime());
//                }
//            }
//        }
//
//        String servletpath = request.getServletPath();//获取请求的路径
//        if(!ToolUtil.isEmpty(servletpath)){
//            String[] split = servletpath.split("/");
//            if(split.length==2){
//                //split[1]获取的是XXX.do
//            }else if(split.length==3){
//                //split[1]获取的是XXX
//                //split[2]获取的是XXX.do
//            }
//        }
//        String userName = "";
//        if (userInfo == null) {
//            userName = returnMap.get("LoginName").toString();
//            operFun = "登录";
//        } else {
//            userName = userInfo.get("user_name").toString();
//        }
//        String operRecoed = userName + " | " + ToolUtil.date2Str(new Date()) + " | " + operFun + "| " + request.getServletPath();
//        logger.info("[操作日志] " + operRecoed);
        map = returnMap;
    }

    public PageData() {
        map = new HashMap<>();
    }

    @Override
    public Object get(Object key) {
        Object obj = null;
        if (map.get(key) instanceof Object[]) {
            Object[] arr = (Object[]) map.get(key);
            obj = request == null ? arr : (request.getParameter((String) key) == null ? arr : arr[0]);
        } else {
            obj = map.get(key);
        }
        return obj;
    }

    public String getString(Object key) {
        return (String) get(key);
    }

    @Override
    public Object put(String key, Object value) {
        return map.put(key, value);
    }

    @Override
    public Object remove(Object key) {
        return map.remove(key);
    }

    public void clear() {
        map.clear();
    }

    public boolean containsKey(Object key) {
        return map.containsKey(key);
    }

    public boolean containsValue(Object value) {
        return map.containsValue(value);
    }

    public Set<Entry<String, Object>> entrySet() {
        return map.entrySet();
    }

    public boolean isEmpty() {
        return map.isEmpty();
    }

    public Set<String> keySet() {
        return map.keySet();
    }

    public void putAll(Map<? extends String, ?> t) {
        map.putAll(t);
    }

    /**
     * 添加前缀的putAll方法
     *
     * @param prefix 前缀
     */
    public void putAllWithPreFix(String prefix, Map<?, ?> t) {
        if (t == null)
            return;
        for (Map.Entry<?, ?> entry : t.entrySet()) {
            map.put(prefix + entry.getKey(), entry.getValue());
        }
    }

    /**
     * 获取前缀为指定前缀的PageData内容，构成新的PageData，并返回。
     *
     * @param prefix 键的前缀
     */
    public PageData getPdWithPreFix(String prefix) {
        if (prefix == null || "".equals(prefix))
            return this;
        int len = prefix.length();
        PageData result = new PageData();
        for (Map.Entry<String, Object> entry : this.entrySet()) {
            String key = (String) entry.getKey();
            if (key.startsWith(prefix)) {
                key = key.substring(len);
                result.put(key, entry.getValue());
            }
        }
        return result;
    }

    public int size() {
        return map.size();
    }

    public Collection<Object> values() {
        return map.values();
    }
}
