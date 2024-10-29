/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.passly.app.Routes;

import atlantafx.base.controls.Card;
import com.passly.app.Components.Password;
import com.passly.app.Components.PasswordGeneratorCard;
import com.passly.app.Components.Sidebar;
import com.passly.app.Context;
import javafx.collections.ListChangeListener;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
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
        body.getChildren().addAll(card);
        VBox passwords = new VBox();
        HBox.setHgrow(passwords, Priority.ALWAYS);
        VBox.setVgrow(passwords, Priority.ALWAYS);
        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setFitToWidth(true);  // Makes the content fit the width of the ScrollPane
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);  // Hide horizontal scrollbar
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);  // Show vertical scrollbar only when needed
        scrollPane.setContent(passwords);
        Context.getUser().getPasswords().addListener((ListChangeListener<Password>) change -> {
            // 'change' is like a report of what happened to the list

            while (change.next()) {
                // change.next() moves through each modification that happened
                // Think of it like turning pages in a change log

                if (change.wasAdded()) {
                    // Checks if this particular change was an addition
                    // Like asking "did someone add new items?"

                    for (Password password : change.getAddedSubList()) {
                        // getAddedSubList() gives you the list of new items that were added
                        // Like saying "show me what was added"
                        Card passwordCard = new Card();
                        TextArea passwordText = new TextArea(password.getPassword());
                        passwordText.setMaxHeight(40);
                        passwordText.setStyle(
                                """
                                -fx-font-family: Poppins;
                                -fx-font-size: 24px;
                                -fx-font-weight: 900;
                                """
                        );
                        passwordCard.setBody(passwordText);
                        passwords.getChildren().add(passwordCard);
                    }
                }
            }
        });

        HBox.setHgrow(card, Priority.ALWAYS);
        body.getChildren().add(scrollPane);
        container.getChildren().add(body);
        getChildren().addAll(container);
    }

}
