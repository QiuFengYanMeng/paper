package com.project.zhang.paper.util;


import com.project.zhang.paper.util.redis.JedisUtil;

import javax.servlet.http.HttpServletRequest;
import java.util.UUID;

/**
 * session 工具类
 * @author 王子洋
 */
public class SessionUtil<T> {

    /**
     * 获得管理员session
     *
     * @param request
     * @return
     */
    public T getSession(HttpServletRequest request) {
        try {
            String token = request.getHeader("token");
            Object object = JedisUtil.getObjectValue(token);
            if (object == null) {
                return null;
            }
            return (T)object;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 获得管理员session
     *
     * @param token
     * @return
     */
    public T getSession(String token) {
        try {
            Object object = JedisUtil.getObjectValue(token);
            if (object == null) {
                return null;
            }
            return (T)object;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 设置不过期session
     *
     * @param model
     * @return
     */
    public String setSessionForever(T model) {
        String token = "forever-"+UUID.randomUUID().toString();
        JedisUtil.setForeverObjectValue(token,model);
        return token;
    }

    /**
     * 设置管理员session
     *
     * @param model
     */
    public String setSession(T model) {
        String token = UUID.randomUUID().toString();
        JedisUtil.setObjectValue(token,model);
        return token;
    }

    /**
     * 设置管理员session
     *
     * @param model
     */
    public  String setSession(T model,String token) {
        JedisUtil.setObjectValue(token,model);
        return token;
    }

    public void removeSession(HttpServletRequest request) {
        String token = request.getHeader("token");
        JedisUtil.del(token);
    }

    public void removeSession(String token) {
        JedisUtil.del(token);
    }
}
