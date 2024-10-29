/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Enum.java to edit this template
 */
package com.passly.app;

/**
 *
 * @author bryan Put the routes and the pages you want in this Route file.
 * Create a value then add the class to getParent. The purpose of this is to get
 * Type Safety and encapsulate routes.
 */
public enum Route {
    LOGIN("/login"),
    REGISTER("/register"),
    DASHBOARD("/dashboard");

    private final String path;

    Route(String path) {
        this.path = path;
    }

    public String getPath() {
        return path;
    }

}
