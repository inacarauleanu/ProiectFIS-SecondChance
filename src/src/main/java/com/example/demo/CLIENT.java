package com.example.demo;

public class CLIENT {
    private String username;
    //private String role;

    public CLIENT(String username){
        this.username= username;
       // this.role=role;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }
}
