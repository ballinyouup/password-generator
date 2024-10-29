/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.passly.app.Components;

import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

/**
 *
 * @author bryan
 */
public class CardHeader extends VBox {

    public CardHeader(String title, String subtitle) {
        getStyleClass().add("card-header");

        getChildren().addAll(new CardTitle(title), new CardSubtitle(subtitle));
        setSpacing(8.0);
    }

    private class CardTitle extends Label {

        public CardTitle(String text) {
            setText(text);
            getStyleClass().add("card-title");
        }
    }

    private class CardSubtitle extends Label {

        public CardSubtitle(String text) {
            setText(text);
            getStyleClass().add("card-subtitle");
        }
    }
}
