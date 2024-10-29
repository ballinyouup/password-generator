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
    private SQL.Table.Column[] columns;

    public SQLInsert() {
        this.query = new StringBuilder();
        this.params = new ArrayList<>();
        insert();
    }

    private SQLInsert insert() {
        query.append("INSERT INTO ");
        return this;
    }

    public SQLInsert table(SQL.Table table) {
        query
                .append("`")
                .append(table.get())
                .append("` ");
        return this;
    }

    public SQLInsert columns(SQL.Table.Column... columns) {
        this.columns = columns;
        query.append("(");
        for (int i = 0; i < columns.length; i++) {
            query
                    .append("`")
                    .append(columns[i].get())
                    .append("`");
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
            if (i < values.length - 1) { // Check if its not the end of the list
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
