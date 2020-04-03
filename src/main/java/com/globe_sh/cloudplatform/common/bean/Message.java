package com.globe_sh.cloudplatform.common.bean;

import java.io.Serializable;

public class Message implements Serializable {

	private static final long serialVersionUID = 4013823148771621185L;
	
	private String msg;
	
	public Message(String msg) {
        this.msg = msg;
    }

	public String getMsg() {
		return msg;
	}
}
