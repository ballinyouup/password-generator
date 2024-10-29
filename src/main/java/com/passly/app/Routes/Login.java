/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.passly.app.Routes;

import com.passly.app.Components.GUILogger;
import com.passly.app.Components.LoginCard;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.StackPane;

/**
 *
 * @author bryan
 */
public class Login extends StackPane {

    public Login() {

        // Create components
        LoginCard loginCard = new LoginCard();
        GUILogger logger = new GUILogger(loginCard.getFields());

        // Set Styling
        setAlignment(logger, Pos.TOP_LEFT);
        setMargin(logger, new Insets(20));
        setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        setAlignment(loginCard, Pos.CENTER);

        getChildren().addAll(logger, loginCard);
    }
}
