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

    public CardHeader(String titleText, String subtitleText) {
        getStyleClass().add("card-header");
        Label title = new Label(titleText);
        title.getStyleClass().add("card-title");
        Label subtitle = new Label(subtitleText);
        subtitle.getStyleClass().add("card-subtitle");
        getChildren().addAll(title, subtitle);
        setSpacing(8.0);
    }
}
