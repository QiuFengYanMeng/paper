package com.project.zhang.paper.entity.user;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

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

    //学生ID
    private int id;

    //登录名
    private String loginName;

    //昵称
    private String nickname;

    //密码
    private String password;

    //账号状态，0正常1禁用
    private int status;

    //用户权限，用于在前端过滤菜单
    private List<String> auth;

}
