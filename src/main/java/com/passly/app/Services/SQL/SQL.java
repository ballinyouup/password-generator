/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.passly.app.Services.SQL;

/**
 *
 * @author bryan
 */
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class SQL {

    private final Connection connection;

    public SQL(Connection connection) {
        this.connection = connection;
    }

    public ResultSet executeQuery(String query, List<Object> params) throws SQLException {
        PreparedStatement stmt = connection.prepareStatement(query);
        setParameters(stmt, params);
        return stmt.executeQuery();
    }

    public int executeUpdate(String query, List<Object> params) throws SQLException {
        PreparedStatement stmt = connection.prepareStatement(query);
        setParameters(stmt, params);
        return stmt.executeUpdate();
    }

    private void setParameters(PreparedStatement stmt, List<Object> params) throws SQLException {
        for (int i = 0; i < params.size(); i++) {
            stmt.setObject(i + 1, params.get(i));
        }
    }
}
