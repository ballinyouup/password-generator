/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.passly.app.Services.SQL;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author bryan
 */
public class SQLSelect {

    private final StringBuilder query;
    private final List<Object> params;

    public SQLSelect() {
        this.query = new StringBuilder();
        this.params = new ArrayList<>();
        select();
    }

    private SQLSelect select() {
        query.append("SELECT ");
        return this;
    }

    public SQLSelect all() {
        query.append("* ");
        return this;
    }

    public SQLSelect column(String column) {
        if (query.toString().contains("SELECT ")) {
            // Remove the last comma and space if exists before adding a new column
            if (query.toString().endsWith(", ")) {
                query.setLength(query.length() - 2); // Remove last comma and space
            }
            query.append("`").append(column).append("`, ");
        }
        return this;
    }

    public SQLSelect from(Table table) {
        query.append("FROM `").append(table.getTableName()).append("` ");
        return this;
    }

    public SQLSelect where() {
        query.append("WHERE ");
        return this;
    }

    public SQLSelect and() {
        query.append("AND ");
        return this;
    }

    public SQLSelect equals(String column, Object value) {
        query.append("`").append(column).append("` = ? ");
        params.add(value);
        return this;
    }

    public String getQuery() {
        // Remove trailing comma and space from the SELECT statement
        if (query.toString().endsWith(", ")) {
            query.setLength(query.length() - 2); // Remove last comma and space
        }
        // Ensure SELECT clause is complete
        if (query.toString().contains("SELECT")) {
            return query.toString().trim();
        } else {
            throw new IllegalStateException("SQL query is malformed: " + query);
        }
    }

    public List<Object> getParams() {
        return params;
    }
}
