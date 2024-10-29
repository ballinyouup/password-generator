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
import com.passly.app.Route;
import com.passly.app.Router;
import com.passly.app.Services.User.UserService;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.Map;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class LoginCard extends Card {

    private final double WIDTH = 640;
    private final double HEIGHT = 320;
    private final SimpleStringProperty username;
    private final SimpleStringProperty password;

    public LoginCard() {
        username = new SimpleStringProperty(this, "Username", "");
        password = new SimpleStringProperty(this, "Password", "");
        setHeader(new CardHeader("Login to Nebula", "Enter your username and password to access your account"));
        setBody(new LoginCardBody());
        setFooter(new LoginCardFooter());
        setMaxSize(WIDTH, HEIGHT);
    }

    private class LoginCardFooter extends VBox {

        public LoginCardFooter() {
            setStyles();
            setChildren();
        }

        private void setChildren() {
            Link registerLink = new Link(Route.REGISTER, "Don't have an account? Click here to register!");
            getChildren().addAll(new LoginCardButton(), registerLink);
        }

        private void setStyles() {
            getStyleClass().add("card-footer");
            setSpacing(28.0);
        }

    }

    private class LoginCardButton extends Button {

        public LoginCardButton() {
            setText("Login");
            setStyles();
            setEventHandlers();
        }

        private void setStyles() {
            getStyleClass().add(Styles.ACCENT);
            setStyle("-fx-font: 16px Poppins");
            setMaxSize(WIDTH, HEIGHT);
        }

        private void setEventHandlers() {
            setOnMouseClicked(e -> {
                handleLoginCardButtonClick();
            });
        }
    }

    private class LoginCardBody extends VBox {

        public LoginCardBody() {
            setStyles();
            setChildren();
        }

        private void setStyles() {
            getStyleClass().add("card-body");
            setSpacing(8.0);
        }

        private void setChildren() {

            TextInput usernameInput = new TextInput(username, "Username");
            PasswordInput passwordInput = new PasswordInput(password, "Password");
            getChildren().addAll(usernameInput, passwordInput);
        }

    }

    private void handleLoginCardButtonClick() {
        try {
            if (username.getValue() == null || username.getValue().toLowerCase().trim().equals("enter username...")) {
                throw new Exception("Empty Username");
            } else if (password.getValue() == null || password.getValue().trim().equals("password")) {
                throw new Exception("Empty Password");
            } else {
                ResultSet dataFromServer = UserService.login(username, password);
                loginSuccess(dataFromServer);
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
