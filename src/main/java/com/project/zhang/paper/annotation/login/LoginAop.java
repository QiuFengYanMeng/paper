package com.project.zhang.paper.annotation.login;

import com.project.zhang.paper.back.Back;
import com.project.zhang.paper.entity.user.SysUser;
import com.project.zhang.paper.util.HttpUtil;
import com.project.zhang.paper.util.SessionUtil;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;

/**
 * 登录拦截
 *
 * @author 王子洋
 * @date 2020-04-23
 */
@Aspect
@Component
public class LoginAop {
    @Around("@annotation(com.project.zhang.paper.annotation.login.Login)")
    public Object beforeSwitchDS(JoinPoint point) throws Throwable {

        //获得当前访问的class
        Class<?> className = point.getTarget().getClass();

        //获得访问的方法名
        String methodName = point.getSignature().getName();
        //得到方法的参数的类型
        Class[] argClass = ((MethodSignature) point.getSignature()).getParameterTypes();
        Object returnVal = null;

        // 得到访问的方法对象
        Method method = className.getMethod(methodName, argClass);
        // 判断是否存在@Login注解
        if (method.isAnnotationPresent(Login.class)) {
            //获取参数列表
            Object[] args = point.getArgs();
            HttpServletRequest request = HttpUtil.getRequest();

            //获取用户信息
            SysUser model = new SessionUtil<SysUser>().getSession(request);
            if (model == null) {
                return new Back<>().noLogin();
            }
            // 获取当前执行的方法
            ProceedingJoinPoint function = (ProceedingJoinPoint) point;
            //执行方法
            returnVal = function.proceed();

        }

        return returnVal;
    }
}
