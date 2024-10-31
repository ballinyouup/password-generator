/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.passly.app.Services.SQL;

/**
 *
 * @author bryan
 */
public class PasswordTable implements Table {

    public static final String TABLE_NAME = "passwords";

    public static final String ID = "id";
    public static final String USER_ID = "user_id";
    public static final String STORED_PASSWORD = "password";
    public static final String CREATED_AT = "created_at";

    @Override
    public String getTableName() {
        return TABLE_NAME;
    }
}
