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
import com.passly.app.Services.PasswordService;
import javafx.collections.ListChangeListener;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
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

        var container = new DashboardContainer();
        VBox.setVgrow(container, Priority.ALWAYS);

        getChildren().addAll(container);
    }

    private class DashboardContainer extends HBox {

        public DashboardContainer() {
            setAlignment(Pos.BOTTOM_LEFT);
            setChildren();
        }

        private void setChildren() {
            Sidebar sidebar = new Sidebar();
            getChildren().add(sidebar);
            getChildren().add(new DashboardBody());

        }
    }

    private class DashboardBody extends VBox {

        public DashboardBody() {
            setChildren();
            setStyles();
        }

        private void setStyles() {
            setBorder(Context.DEBUG ? Border.stroke(Color.WHITE) : null);
            setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
            setAlignment(Pos.TOP_CENTER);
            setPadding(new Insets(12.0));

        }

        private void setChildren() {
            HBox.setHgrow(this, Priority.ALWAYS);
            VBox.setVgrow(this, Priority.ALWAYS);
            var card = new PasswordGeneratorCard();
            HBox.setHgrow(card, Priority.ALWAYS);

            getChildren().addAll(card, new PasswordsContainer());
        }
    }

    private class PasswordsContainer extends ScrollPane {

        private final VBox passwordItemsContainer;

        public PasswordsContainer() {
            setFitToWidth(true);
            setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
            setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);

            passwordItemsContainer = new VBox();
            setContent(passwordItemsContainer);

            Context.getUser().getPasswords().addListener((ListChangeListener<Password>) change -> {
                while (change.next()) {
                    if (change.wasAdded()) {
                        for (Password password : change.getAddedSubList()) {
                            addPasswordCard(password);
                        }
                    }
                    if (change.wasRemoved()) {
                        for (Password password : change.getRemoved()) {
                            passwordItemsContainer.getChildren().removeIf(node -> {
                                if (node instanceof Card card) {
                                    TextArea passwordText = (TextArea) card.getBody();
                                    return passwordText.getText().equals(password.getPassword());
                                }
                                return false;
                            });
                        }
                    }
                }
            });

            for (Password password : Context.getUser().getPasswords()) {
                addPasswordCard(password);
            }
        }

        private void addPasswordCard(Password password) {
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
            Button delete = new Button("Delete");
            delete.setOnMouseClicked(e -> {
                Context.getUser().getPasswords().remove(password);
                PasswordService.deletePassword(password.getPassword());
            });
            passwordCard.setFooter(delete);
            passwordItemsContainer.getChildren().add(passwordCard);
        }
    }

}
