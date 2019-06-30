package com.yc.entity;

public class User {
	private Integer id;
	private String name;
	private String password;
	private Integer tAge;
	private String address;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Integer gettAge() {
		return tAge;
	}

	public void settAge(Integer tAge) {
		this.tAge = tAge;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

}
