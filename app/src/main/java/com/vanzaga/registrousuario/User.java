package com.vanzaga.registrousuario;

public class User {

    // Atributos
    private String username;
    private String name;
    private String password;

    // Constructor
    public User(String username, String name, String password) {
        this.username = username;
        this.name = name;
        this.password = password;
    }

    // Métodos getter para obtener los valores de los atributos
    public String getUsername() {
        return username;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }
}