/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.passly.app.Services;

import java.util.logging.Level;
import java.util.logging.Logger;
import com.mysql.cj.jdbc.MysqlDataSource;
import com.passly.app.Context;
import java.sql.Connection;
import java.sql.SQLException;

/**
 *
 * @author bryan
 */
public class Database {

    public static Connection getConnection() {
        Connection cnx = null;

        MysqlDataSource datasource = new MysqlDataSource();
        datasource.setServerName(Context.getEnv().get("SERVER_NAME"));
        datasource.setUser(Context.getEnv().get("USERNAME"));
        datasource.setPassword(Context.getEnv().get("PASSWORD"));
        datasource.setDatabaseName(Context.getEnv().get("DB_NAME"));
        datasource.setPortNumber(Integer.parseInt(Context.getEnv().get("DB_PORT")));

        try {
            cnx = datasource.getConnection();
        } catch (SQLException ex) {
            Logger.getLogger("Get connection ->" + Database.class.getName()).log(Level.SEVERE, null, ex);
        }

        return cnx;
    }
}
