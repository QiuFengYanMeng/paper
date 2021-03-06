package com.project.zhang.paper.util;
 
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
 
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
 
/** Http工具类，返回HttpServletRequest对象和HttpServletResponse对象
 * @author 王子洋
 * @date 2020-04-23
 * */
public class HttpUtil {
    
    /** 返回一个HttpServletRequest
     * @return request请求体
     * */
    public static HttpServletRequest getRequest(){
        return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
    }
 
    /** 返回一个HttpServletResponse
     * @return response响应流
     * */
    public static HttpServletResponse getResponse(){
        return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getResponse();
    }
}