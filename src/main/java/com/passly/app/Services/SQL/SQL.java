/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.passly.app.Services.SQL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author bryan
 */
public class SQL {

    public enum Table {
        USERS("users");

        private final String tableName;

        Table(String name) {
            this.tableName = name;
        }

        public String get() {
            return tableName;
        }

        public enum Column {
            USERNAME("username"),
            PASSWORD("password"),
            FULL_NAME("full_name"),
            PHONE("phone"),
            GENDER("gender"),
            PICTURE("picture");

            private final String columnName;

            Column(String name) {
                this.columnName = name;
            }

            public String get() {
                return columnName;
            }
        }
    }

    private final Connection connection;

    public SQL(Connection connection) {
        this.connection = connection;
    }

    // Execution methods
    public ResultSet executeSelect(SQLSelect select) throws SQLException {
        PreparedStatement stmt = connection.prepareStatement(select.getQuery());
        setParameters(stmt, select.getParams());
        return stmt.executeQuery();
    }

    public int executeInsert(SQLInsert insert) throws SQLException {
        PreparedStatement stmt = connection.prepareStatement(insert.getQuery());
        setParameters(stmt, insert.getParams());
        return stmt.executeUpdate();
    }

    private void setParameters(PreparedStatement stmt, List<Object> params) throws SQLException {
        for (int i = 0; i < params.size(); i++) {
            stmt.setObject(i + 1, params.get(i));
        }
    }
}
