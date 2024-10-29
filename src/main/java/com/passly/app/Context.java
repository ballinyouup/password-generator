/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.passly.app;

import com.passly.app.Models.User;
import io.github.cdimascio.dotenv.Dotenv;
import javafx.beans.property.SimpleObjectProperty;

/**
 *
 * @author bryan
 */
public class Context {

    private static SimpleObjectProperty user;
    public static boolean DEBUG = false;
    public static boolean GUI_DEBUG = false;
    public static Dotenv env;

    public static void setUser(User user) {
        Context.user = new SimpleObjectProperty(user);
    }

    public static User getUser() {
        if (user == null) {
            Context.user = new SimpleObjectProperty(new User());
        }
        return (User) user.getValue();
    }

    public static void setEnv(Dotenv env) {
        Context.env = env;
    }

    public static Dotenv getEnv() {
        return env;
    }
}
