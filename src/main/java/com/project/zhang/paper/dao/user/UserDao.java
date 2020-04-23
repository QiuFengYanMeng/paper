package com.project.zhang.paper.dao.user;

import com.project.zhang.paper.entity.user.SysUser;
import org.apache.ibatis.annotations.Mapper;

/**
 *  <p>
 *      用户模块数据层接口
 *  </p>
 *
 * @author 王子洋
 * @date 2020-04-23
 * */
@Mapper
public interface UserDao {

    /**
     *  登录时的查询
     *
     * @param model 数据实体
     * @return SysUser系统用户实体类
     * */
    SysUser findOnLogin (SysUser model);
}
