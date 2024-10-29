/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.passly.app.Services.User;

import com.passly.app.Route;
import com.passly.app.Router;
import com.passly.app.Services.Database;
import com.passly.app.Services.SQL.SQL;
import com.passly.app.Services.SQL.SQLInsert;
import com.passly.app.Services.SQL.SQLSelect;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.util.Map;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.Alert;

/**
 *
 * @author bryan
 */
public class UserService {

    public static boolean verifyFields(Map<String, SimpleStringProperty> fields) {
        for (SimpleStringProperty field : fields.values()) {
            if (!field.getName().equals("Image Path") && field.getValue() == null) {
                Alert alert = new Alert(Alert.AlertType.WARNING, field.getName() + " is Empty!");
                alert.show();
                return false;
            }
        }
        return true;
    }

    public static boolean checkUsername(SimpleStringProperty username) {
        try {
            SQL sql = new SQL(Database.getConnection());

            SQLSelect select = new SQLSelect();
            select.all()
                    .from()
                    .table(SQL.Table.USERS)
                    .where()
                    .equals(SQL.Table.Column.USERNAME, username.getValue());

            if (sql.executeSelect(select).next()) {
                Alert alert = new Alert(Alert.AlertType.WARNING, "This username is already taken. Choose another one");
                alert.show();
                return true;
            }
        } catch (SQLException ex) {
            Alert alert = new Alert(Alert.AlertType.WARNING, "SQL Error: " + ex.getMessage());
            alert.show();
        }
        return false;
    }

    public static void registerUser(Map<String, SimpleStringProperty> fields, SimpleObjectProperty image) {
        if (verifyFields(fields) && !checkUsername(fields.get("Username"))) {
            SQL sql = new SQL(Database.getConnection());
            SQLInsert insert = new SQLInsert();
            try {
                if (fields.get("Image Path").getValue() != null) {
                    image.set(new FileInputStream(new File(fields.get("Image Path").getValue())));
                }

                insert
                        .table(SQL.Table.USERS)
                        .columns(SQL.Table.Column.FULL_NAME, SQL.Table.Column.USERNAME,
                                SQL.Table.Column.PASSWORD, SQL.Table.Column.PHONE,
                                SQL.Table.Column.GENDER, SQL.Table.Column.PICTURE)
                        .values(fields.get("Full Name").get(), fields.get("Username").get(), fields.get("Password").get(),
                                fields.get("Phone Number").get(), fields.get("Gender").get(), image.get());

                if (sql.executeInsert(insert) != 0) {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION, "Your Account has been created!");
                    alert.show();
                    Router.push(Route.LOGIN);
                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR, "Error: Check your information!");
                    alert.show();
                }
            } catch (SQLException ex) {
                Alert alert = new Alert(Alert.AlertType.WARNING, "SQL Error: " + ex.getMessage());
                alert.show();
            } catch (FileNotFoundException ex) {
                Alert alert = new Alert(Alert.AlertType.WARNING, "File Input Error: " + ex.getMessage());
                alert.show();
            }
        }
    }

}
