package com.project.zhang.paper.service.user;

import com.project.zhang.paper.back.Back;
import com.project.zhang.paper.dao.user.UserDao;
import com.project.zhang.paper.entity.user.SysUser;
import com.project.zhang.paper.util.SessionUtil;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

/**
 *  <p>
 *      用户模块，服务层组件
 *  </p>
 *
 * @author 王子洋
 * @date 2020-04-23
 * */
@Service
@AllArgsConstructor
public class UserService {

    //数据层组件
    private UserDao userDao;

    /**
     *  登录
     *
     * @param model 数据实体
     * @return 业务处理结果
     * */
    public Back login (SysUser model) {
        //从数据库查询用户
        SysUser user = userDao.findOnLogin(model);
        if (user == null) {
            return new Back().error("登录名或密码不正确");
        }

        if (user.getStatus() == 1) {
            return new Back().error("账号已被禁用，请联系平台管理员");
        }

        //将用户信息存到Redis当中，下发token
        String token = new SessionUtil<SysUser>().setSession(user).replace("-" , "");
        return new Back(user).msg("登录成功").put("token" , token);
    }
}
