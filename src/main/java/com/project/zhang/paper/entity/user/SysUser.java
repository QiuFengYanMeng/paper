package com.project.zhang.paper.entity.user;

import lombok.Data;

import java.io.Serializable;

/**
 *  <p>
 *      系统用户
 *  </p>
 *
 * @author 王子洋
 * @date 2020-04-23
 * */
@Data
public class SysUser implements Serializable {

    //登录名
    private String loginName;

    //昵称
    private String nickname;

    //密码
    private String password;

    //账号状态，0正常1禁用
    private int status;

}
