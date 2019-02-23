package com.ronald.entity;

import lombok.Data;

@Data
public class Address {

	private String code;
	private String addr;
	public Address() {
		super();
	}
	public Address(String code, String addr) {
		super();
		this.code = code;
		this.addr = addr;
	}
	@Override
	public String toString() {
		return "Address [code=" + code + ", addr=" + addr + "]";
	}
	
	
}
