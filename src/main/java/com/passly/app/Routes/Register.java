/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.passly.app.Routes;

import com.passly.app.Components.GUILogger;
import com.passly.app.Components.RegisterCard;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.StackPane;
import static javafx.scene.layout.StackPane.setAlignment;

/**
 *
 * @author bryan
 */
public class Register extends StackPane {

    public Register() {
        setPadding(new Insets(20.0));

        RegisterCard registerCard = new RegisterCard();
        GUILogger logger = new GUILogger(registerCard.getFields());

        // Set Styling
        setAlignment(logger, Pos.TOP_LEFT);
        setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        setAlignment(registerCard, Pos.CENTER);

        getChildren().addAll(logger, registerCard);
    }
}
