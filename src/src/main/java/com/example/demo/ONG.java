package com.example.demo;

public class ONG {
    private int id_ong;
    private String firstname;
    private String lastname;
    private String username;
    private String password;
    private String email;
   // private String role;

    public ONG(int id_ong,String firstname, String lastname, String username,String email) {
        this.id_ong=id_ong;
        this.firstname = firstname;
        this.lastname = lastname;
        this.username=username;
        this.email = email;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    public int getId_ong() {
        return id_ong;
    }

    public void setId_ong(int id_admin) {
        this.id_ong = id_ong;
    }
}

