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

    public SQLSelect from() {
        query.append("FROM ");
        return this;
    }

    public SQLSelect table(SQL.Table table) {
        query
                .append("`")
                .append(table.get())
                .append("` ");
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

    public SQLSelect equals(SQL.Table.Column column, Object value) {
        query
                .append("`")
                .append(column.get())
                .append("` = ? ");
        params.add(value);
        return this;
    }

    public String getQuery() {
        return query.toString().trim();
    }

    public List<Object> getParams() {
        return params;
    }
}
