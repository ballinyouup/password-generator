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
import javafx.scene.Node;
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
        // Sidebar
        // Main Content
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

        public PasswordsContainer() {

            setFitToWidth(true);  // Makes the content fit the width of the ScrollPane
            setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);  // Hide horizontal scrollbar
            setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);  // Show vertical scrollbar only when needed
            setContent(new PasswordItems());

        }

        private class PasswordItems extends VBox {

            public PasswordItems() {
                setChildren();
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
                                getChildren().add(passwordCard);
                            }
                        }
                    }
                });
            }

            private void setChildren() {
                HBox.setHgrow(this, Priority.ALWAYS);
                VBox.setVgrow(this, Priority.ALWAYS);
            }

        }
    }

}
