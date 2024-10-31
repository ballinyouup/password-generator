/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.passly.app.Services.SQL;

/**
 *
 * @author bryan
 */
public class UserTable implements Table {

    public static final String TABLE_NAME = "users";
    public static final String ID = "id";
    public static final String FULL_NAME = "full_name";
    public static final String USERNAME = "username";
    public static final String PASSWORD = "password";
    public static final String PHONE = "phone";
    public static final String GENDER = "gender";
    public static final String PICTURE = "picture";

    @Override
    public String getTableName() {
        return TABLE_NAME;
    }
}
