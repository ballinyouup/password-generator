/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.passly.app.Services;

import com.passly.app.Components.LoginCard;
import com.passly.app.Components.Password;
import com.passly.app.Models.User;
import com.passly.app.Route;
import com.passly.app.Router;
import com.passly.app.Services.SQL.PasswordTable;
import com.passly.app.Services.SQL.SQL;
import com.passly.app.Services.SQL.SQLInsert;
import com.passly.app.Services.SQL.SQLSelect;
import com.passly.app.Services.SQL.UserTable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.Alert;

/**
 *
 * @author bryan
 */
public class UserService {

    private static final SQL sql = new SQL(Database.getConnection());
    private static final UserTable userTable = new UserTable();

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
            SQLSelect select = new SQLSelect()
                    .all()
                    .from(userTable)
                    .where()
                    .equals(UserTable.USERNAME, username.getValue());

            if (sql.executeQuery(select.getQuery(), select.getParams()).next()) {
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

    public static ResultSet login(SimpleStringProperty username, SimpleStringProperty password) {
        try {
            SQLSelect select = new SQLSelect()
                    .all()
                    .from(userTable)
                    .where()
                    .equals(UserTable.USERNAME, username.getValue())
                    .and()
                    .equals(UserTable.PASSWORD, password.getValue());

            System.out.println("Generated SQL Query: " + select.getQuery());

            ResultSet rs = sql.executeQuery(select.getQuery(), select.getParams());

            if (rs.next()) {
                return rs;
            } else {
                Alert alert = new Alert(Alert.AlertType.WARNING, "Invalid Username or Password");
                alert.show();
                return null;
            }
        } catch (SQLException ex) {
            Logger.getLogger(LoginCard.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public static void registerUser(Map<String, SimpleStringProperty> fields, SimpleObjectProperty image) {
        if (verifyFields(fields) && !checkUsername(fields.get("Username"))) {
            try {
                if (fields.get("Image Path").getValue() != null) {
                    image.set(new FileInputStream(new File(fields.get("Image Path").getValue())));
                }

                SQLInsert insert = new SQLInsert()
                        .table(userTable)
                        .columns(UserTable.FULL_NAME, UserTable.USERNAME, UserTable.PASSWORD,
                                UserTable.PHONE, UserTable.GENDER, UserTable.PICTURE)
                        .values(fields.get("Full Name").get(), fields.get("Username").get(),
                                fields.get("Password").get(), fields.get("Phone Number").get(),
                                fields.get("Gender").get(), image.get());

                if (sql.executeUpdate(insert.getQuery(), insert.getParams()) != 0) {
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

    public static List<Password> loadUserPasswords(int userId) {
        List<Password> passwordList = new ArrayList<>();
        try {
            SQLSelect select = new SQLSelect()
                    .all()
                    .from(new PasswordTable())
                    .where()
                    .equals(PasswordTable.USER_ID, userId);

            ResultSet rs = sql.executeQuery(select.getQuery(), select.getParams());
            while (rs.next()) {
                Password password = new Password(rs.getString(PasswordTable.STORED_PASSWORD));
                System.out.println("Retrieved password: " + password.getPassword());
                passwordList.add(password);
            }
        } catch (SQLException ex) {
            System.err.println("SQL Error: " + ex.getMessage());
        }
        return passwordList;
    }

    public static User loadUserWithPasswords(int userId) {
        try {
            // Query user info
            SQLSelect select = new SQLSelect()
                    .all()
                    .from(userTable)
                    .where()
                    .equals(UserTable.ID, userId);

            ResultSet rs = sql.executeQuery(select.getQuery(), select.getParams());
            if (rs.next()) {
                User user = User.fromResultSet(rs);
                List<Password> passwords = loadUserPasswords(userId);
                user.setPasswords(passwords);
                return user;
            }
        } catch (SQLException ex) {
            System.err.println("SQL Error: " + ex.getMessage());
        }
        return null;
    }
}
