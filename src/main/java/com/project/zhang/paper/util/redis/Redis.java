package com.project.zhang.paper.util.redis;

import lombok.Data;

/**
 * Redis 实体类
 *
 * @author 王子洋
 * @date 2020-04-23
 */
@Data
public class Redis {
    /**
     * 地址
     */
    private String address;

    /**
     * 密码
     */
    private String password;

    public Redis(String address) {
        this.address = address;
    }

    public Redis(String address, String password) {
        this.address = address;
        this.password = password;
    }
}
