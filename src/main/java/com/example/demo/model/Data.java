package com.example.demo.model;

import org.springframework.data.annotation.Id;

public class Data {
	
	@Id
	private String id;
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	private String todo;
    private Boolean bool;


    public Boolean getBool() {
        return this.bool;
    }

    public void setBool(Boolean bool) {
        this.bool = bool;
    }

    public String getTodo() {
        return this.todo;
    }

    public void setTodo(String todo) {
        this.todo = todo;
    }

    public Data(){}

    public Data(String todo,Boolean bool ){
        this.todo = todo;
        this.bool = bool;
    
	}
}
