package com.project.zhang.paper.annotation.permission;

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
 * 拦截权限标识方法 ，权限验证
 *
 * @author 王子洋
 * @date 2020-04-23
 */
@Aspect
@Component
public class PermissionAop {

    @Around("@annotation(com.project.zhang.paper.annotation.permission.Permission)")
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
        // 判断是否存在@Permission注解
        if (method.isAnnotationPresent(Permission.class)) {
            Permission annotation = method.getAnnotation(Permission.class);
            //获取参数列表
            Object[] args = point.getArgs();
            HttpServletRequest request = HttpUtil.getRequest();

            //获取用户信息
            SysUser model = new SessionUtil<SysUser>().getSession(request);
            if (model == null) {
                return new Back<>().noLogin();
            }

            //先默认放行，方便调试，以后再解开
            boolean flag = true;
            /*if ("system".equals(model.getRole().getEnName())) {
                flag = true;
            } else {
                for (Authority e : model.getAuthorityList()) {
                    String[] split = annotation.value().split(",");
                    for (String permission : split) {
                        if (StringUtils.isNotBlank(e.getSign()) && e.getSign().equals(permission)) {
                            flag = true;
                            break;
                        }
                    }
                    if (flag) {
                        break;
                    }
                }
            }*/

            if (!flag) {
                return new Back<>().msg("您没有权限进行该操作");
            }

            // 获取当前执行的方法
            ProceedingJoinPoint function = (ProceedingJoinPoint) point;
            //执行方法
            returnVal = function.proceed();
        }
        return returnVal;

    }
}
