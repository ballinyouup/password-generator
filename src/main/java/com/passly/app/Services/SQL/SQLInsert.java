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
public class SQLInsert {

    private final StringBuilder query;
    private final List<Object> params;
    private String[] columns;

    public SQLInsert() {
        this.query = new StringBuilder();
        this.params = new ArrayList<>();
        insert();
    }

    private SQLInsert insert() {
        query.append("INSERT INTO ");
        return this;
    }

    public SQLInsert table(Table table) {
        query.append("`").append(table.getTableName()).append("` ");
        return this;
    }

    public SQLInsert columns(String... columns) {
        this.columns = columns;
        query.append("(");
        for (int i = 0; i < columns.length; i++) {
            query.append("`").append(columns[i]).append("`");
            if (i < columns.length - 1) {
                query.append(", ");
            }
        }
        query.append(") ");
        return this;
    }

    public SQLInsert values(Object... values) {
        if (values.length != columns.length) {
            throw new IllegalArgumentException("Number of values must match number of columns");
        }
        query.append("VALUES (");

        for (int i = 0; i < values.length; i++) {
            query.append("?");
            if (i < values.length - 1) {
                query.append(", ");
            }
            params.add(values[i]);
        }
        query.append(")");
        return this;
    }

    public String getQuery() {
        return query.toString().trim();
    }

    public List<Object> getParams() {
        return params;
    }
}
