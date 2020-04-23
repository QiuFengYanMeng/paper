package com.project.zhang.paper.controller.login;

import com.project.zhang.paper.annotation.validate.ValidateFiled;
import com.project.zhang.paper.annotation.validate.ValidateGroup;
import com.project.zhang.paper.back.Back;
import com.project.zhang.paper.entity.user.SysUser;
import com.project.zhang.paper.service.user.UserService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *  <p>
 *      登录、注册、退出登录模块的控制器
 *  </p>
 *
 * @author 王子洋
 * @date 2020-04-23
 * */
@RestController
@RequestMapping(value = "${controller.prefix}/system")
@AllArgsConstructor
public class LoginController {

    //服务层组件
    private UserService userService;

    /**
     *  登录
     *
     * @param model 数据实体
     * @return 业务处理结果
     * */
    @PostMapping(value = "/login")
    @ValidateGroup(fileds = {
            @ValidateFiled(index = 0 , filedName = "loginName" , notNull = true , msg = "缺少loginName"),
            @ValidateFiled(index = 0 , filedName = "password" , notNull = true , msg = "缺少password")
    })
    public Back<SysUser> login (@RequestBody SysUser model) {
        return userService.login(model);
    }
}
