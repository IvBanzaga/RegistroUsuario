package com.vanzaga.registrousuario;


import java.io.Serializable;

public class User implements Serializable {

    // Atributos de la clase User
    private String username;
    private String name;
    private String password;

    // Constructor de la clase User
    public User(String username, String name, String password) {
        this.username = username;
        this.name = name;
        this.password = password;
    }

    // Métodos getter y setter para los atributos de la clase User
    public String getUsername() {
        return username;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}