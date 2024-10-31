/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.passly.app.Services;

/**
 *
 * @author bryan
 */
import com.passly.app.Services.SQL.PasswordTable;
import com.passly.app.Services.SQL.SQL;
import com.passly.app.Services.SQL.SQLInsert;
import com.passly.app.Services.SQL.SQLSelect;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.control.Alert;

public class PasswordService {

    private static final SQL sql = new SQL(Database.getConnection());
    private static final PasswordTable passwordTable = new PasswordTable();

    public static boolean createPassword(int userId, String password) {
        try {
            SQLInsert insert = new SQLInsert()
                    .table(passwordTable)
                    .columns(PasswordTable.USER_ID, PasswordTable.STORED_PASSWORD)
                    .values(userId, password);

            if (sql.executeUpdate(insert.getQuery(), insert.getParams()) != 0) {
                return true;
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Failed to create password.");
                alert.show();
                return false;
            }
        } catch (SQLException ex) {
            Alert alert = new Alert(Alert.AlertType.WARNING, "SQL Error: " + ex.getMessage());
            alert.show();
            return false;
        }
    }

    public static ResultSet getPasswords(int userId) {
        try {
            SQLSelect select = new SQLSelect()
                    .all()
                    .from(passwordTable)
                    .where()
                    .equals(PasswordTable.USER_ID, userId);

            return sql.executeQuery(select.getQuery(), select.getParams());
        } catch (SQLException ex) {
            Logger.getLogger(PasswordService.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public static boolean deletePassword(String password) {
        try {
            String query = "DELETE FROM `" + passwordTable.getTableName() + "` WHERE `" + PasswordTable.STORED_PASSWORD + "` = ?";
            if (sql.executeUpdate(query, List.of(password)) != 0) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION, "Password deleted successfully!");
                alert.show();
                return true;
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Failed to delete password.");
                alert.show();
                return false;
            }
        } catch (SQLException ex) {
            Alert alert = new Alert(Alert.AlertType.WARNING, "SQL Error: " + ex.getMessage());
            alert.show();
            return false;
        }
    }
}
