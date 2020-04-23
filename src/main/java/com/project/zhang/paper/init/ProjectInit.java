package com.project.zhang.paper.init;

import com.project.zhang.paper.util.redis.JedisUtil;
import com.project.zhang.paper.util.redis.Redis;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 *  <p>
 *      项目启动初始化
 *  </p>
 *
 * @author 王子洋
 * @date 2020-04-23
 * */
@Component
public class ProjectInit implements ApplicationRunner {

    /**
     * redis 地址
     */
    @Value("${spring.redis.host}")
    private String redisUrl;

    /**
     * redis 端口
     */
    @Value("${spring.redis.port}")
    private String redisPort;

    /**
     * redis 密码
     */
    @Value("${spring.redis.password}")
    private String redisPassword;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        List<Redis> redisList = new ArrayList();
        redisList.add(new Redis(redisUrl+":"+redisPort,redisPassword));
        JedisUtil.init(redisList);
        System.out.println("初始化Redis完成****************");
    }
}
