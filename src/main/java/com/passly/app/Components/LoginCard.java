/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.passly.app.Components;

/**
 *
 * @author bryan
 */
import atlantafx.base.controls.Card;
import atlantafx.base.theme.Styles;
import com.passly.app.Context;
import com.passly.app.Models.User;
import com.passly.app.Services.Database;
import com.passly.app.Route;
import com.passly.app.Router;
import com.passly.app.Services.SQL.SQL;
import com.passly.app.Services.SQL.SQLSelect;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

public class LoginCard extends Card {

    private final double PADDING_X = 20.0;
    private final double PADDING_Y = 20.0;
    private final double WIDTH = 640;
    private final double HEIGHT = 320;
    private final SimpleStringProperty username;
    private final SimpleStringProperty password;

    public LoginCard() {
        username = new SimpleStringProperty(this, "Username", "");
        password = new SimpleStringProperty(this, "Password", "");
        CardHeader cardHeader = new CardHeader("Login to Nebula", "Enter your username and password to access your account");
        setHeader(cardHeader);
        setBody(createBody());
        setFooter(createFooter());
        setMaxSize(WIDTH, HEIGHT);
    }

    private VBox createFooter() {
        VBox cardFooter = new VBox();
        // Footer Styles
        cardFooter.getStyleClass().add("card-footer");
        cardFooter.setSpacing(28.0);

        Button loginButton = new Button("Login");
        // Button Styles
        loginButton.getStyleClass().add(Styles.ACCENT);
        loginButton.setStyle("-fx-font: 16px Poppins");
        loginButton.setMaxSize(WIDTH, HEIGHT);

        // Event Handlers for Login Button
        loginButton.setOnMouseClicked(e -> {
            handleLoginButtonClick();
        });

        // Add Link
        Link registerLink = new Link(Route.REGISTER, "Don't have an account? Click here to register!");

        // Add Children
        cardFooter.getChildren().addAll(loginButton, registerLink);
        return cardFooter;
    }

    private VBox createBody() {
        VBox cardBody = new VBox();
        // Card Body Styles
        cardBody.getStyleClass().add("card-body");
        cardBody.setSpacing(8.0);

        // Inputs
        TextInput usernameInput = new TextInput(username, "Username");
        PasswordInput passwordInput = new PasswordInput(password, "Password");
        cardBody.getChildren().addAll(usernameInput.getLabel(), usernameInput, passwordInput.getLabel(), passwordInput);

        return cardBody;
    }

    private void loginUser() {
        try {
            SQL sql = new SQL(Database.getConnection());

            SQLSelect select = new SQLSelect();
            select.all()
                    .from()
                    .table(SQL.Table.USERS)
                    .where()
                    .equals(SQL.Table.Column.USERNAME, username.getValue())
                    .and()
                    .equals(SQL.Table.Column.PASSWORD, password.getValue());

            ResultSet rs = sql.executeSelect(select);

            if (rs.next()) {
                loginSuccess(rs);
            } else {
                Alert alert = new Alert(Alert.AlertType.WARNING, "Invalid Username or Password");
                alert.show();
            }
        } catch (SQLException ex) {
            Logger.getLogger(LoginCard.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void handleLoginButtonClick() {
        try {
            if (username.getValue() == null || username.getValue().toLowerCase().trim().equals("enter username...")) {
                throw new Exception("Empty Username");
            } else if (password.getValue() == null || password.getValue().trim().equals("password")) {
                throw new Exception("Empty Password");
            } else {
                loginUser();
            }
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.WARNING, e.getMessage());
            alert.show();
        }

    }

    private void loginSuccess(ResultSet rs) throws SQLException {
        Context.setUser(User.fromResultSet(rs));
        Router.push(Route.DASHBOARD);
    }

    public Map<String, SimpleStringProperty> getFields() {
        Map<String, SimpleStringProperty> list = new LinkedHashMap<>();
        list.put(username.getName(), username);
        list.put(password.getName(), password);
        return list;
    }

}
