package com.zt.poseidon.common.entity;

import com.zt.poseidon.common.constant.Constants;
import lombok.Data;

import java.io.Serializable;


@Data
public class Result<T> implements Serializable{

    private static final long serialVersionUID = 1L;
    /**
     * 成功状态
     */
    private boolean success;
    /**
     * 状态响应码
     */
	private String code;
    /**
     * 提示信息
     */
	private String msg;
    /**
     * 附加数据
     */
	private T data = null;

	public Result() {

	}
	public Result(boolean success) {
		this.success = success;
	}

    public Result(Constants.Status status) {
        this.msg = status.getMsg();
        this.code=status.getCode();
    }

    public Result(boolean success, Constants.Status status) {
        this.success = success;
        this.msg = status.getMsg();
        this.code=status.getCode();
    }

    public Result(boolean success, String msg) {
		this.success = success;
		this.msg = msg;
	}

	public Result(boolean success, String code, String msg) {
		this.success = success;
		this.code = code;
		this.msg = msg;
	}

	public Result(boolean success, String code, String msg, T data) {
		this.success = success;
		this.code = code;
		this.msg = msg;
		this.data = data;
	}

}
