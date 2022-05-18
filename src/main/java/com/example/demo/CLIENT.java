package com.example.demo;

public class CLIENT {
    private String username;
    private String role;

    public CLIENT(String username, String role){
        this.username= username;
        this.role=role;
    }

    public String getRole(){
        return this.role;
    }
}
