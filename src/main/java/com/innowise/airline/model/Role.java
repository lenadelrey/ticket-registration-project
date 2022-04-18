package com.innowise.airline.model;

public enum Role {
//    TODO: одна строчка - одно значение енама. По крайней мере, если у него есть поля
    ROLE_USER("ROLE_USER"), ROLE_ADMIN("ROLE_ADMIN");

    private final String role;

    Role(String role) {
        this.role = role;
    }

//    TODO: зачем писать руками конструктор и геттер, если есть ломбок?
    public String getRole() {
        return role;
    }
}
