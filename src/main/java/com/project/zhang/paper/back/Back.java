package com.project.zhang.paper.back;

import lombok.*;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * 响应信息主体
 *
 * @param <T>
 * @author 王子洋
 */
@Builder
@ToString
@Accessors(chain = true)
@AllArgsConstructor
public class Back<T> implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 运行成功
     */
    final String RUN_SUCCESS = "0";

    /**
     * 运行失败
     */
    final String RUN_ERROR = "1";

    /**
     * 业务失败
     */
    final Boolean BACK_ERROR = false;

    /**
     * 业务成功
     */
    final Boolean BACK_SUCCESS = true;

    /**
     * 未登录
     */
    final String RUN_NOT_LOGIN = "1001";


    @Getter
    @Setter
    private String code = RUN_SUCCESS;

    @Getter
    @Setter
    private String msg = "success";


    @Getter
    @Setter
    private Boolean state = BACK_SUCCESS;

    @Getter
    @Setter
    private Long count = null;


    @Getter
    @Setter
    private T data;

    public Back() {
        super();
    }

    public Back(T data) {
        super();
        this.data = data;
    }

    public Back(T data, String msg) {
        super();
        this.data = data;
        this.msg = msg;
    }

    public Back(T data, Long count) {
        super();
        this.data = data;
        this.count = count;
    }


    public Back(Throwable e) {
        super();
        this.msg = e.getMessage();
        this.code = RUN_ERROR;
    }


    public Back<T> error(String msg) {
        this.state = BACK_ERROR;
        this.msg = msg;
        return this;
    }

    public Back<T> msg(String msg) {
        this.msg = msg;
        return this;
    }

    public Back<T> noLogin() {
        this.code = RUN_NOT_LOGIN;
        return this;
    }

    public Back<T> runError(String msg) {
        this.code = RUN_ERROR;
        this.msg = msg;
        return this;
    }

}
