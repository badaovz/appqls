package com.example.datvl.appqls;

public class User {
    private int IdUser;
    private String name, password;

    public User(int idUser, String name, String password) {
        IdUser = idUser;
        this.name = name;
        this.password = password;
    }

    public int getIdUser() {
        return IdUser;
    }

    public void setIdUser(int idUser) {
        IdUser = idUser;
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
}
