/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.passly.app.Routes;

import atlantafx.base.theme.Styles;
import com.passly.app.Components.PasswordGeneratorCard;
import com.passly.app.Components.Sidebar;
import com.passly.app.Context;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.Border;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

/**
 *
 * @author bryan
 */
public class Dashboard extends StackPane {

    public Dashboard() {
        // Styles
        setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        setAlignment(Pos.CENTER);

        // Container
        HBox container = new HBox();
        container.setAlignment(Pos.BOTTOM_LEFT);
        VBox.setVgrow(container, Priority.ALWAYS);

        // Sidebar
        Sidebar sidebar = new Sidebar();
        container.getChildren().add(sidebar);

        // Main Content
        VBox body = new VBox();
        HBox.setHgrow(body, Priority.ALWAYS);
        VBox.setVgrow(body, Priority.ALWAYS);
        body.setBorder(Context.DEBUG ? Border.stroke(Color.WHITE) : null);
        body.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);

        body.setAlignment(Pos.TOP_CENTER);
        body.setPadding(new Insets(12.0));
        var card = new PasswordGeneratorCard();
        HBox.setHgrow(card, Priority.ALWAYS);
        body.getChildren().addAll(card);
        container.getChildren().add(body);

        getChildren().addAll(container);
    }

}
