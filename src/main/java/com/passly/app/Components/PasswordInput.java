/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.passly.app.Components;

import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

/**
 *
 * @author bryan
 */
public class PasswordInput extends VBox {

    public PasswordInput(SimpleStringProperty password, String labelText) {
        TextField textInput = new TextField();
        textInput.textProperty().bindBidirectional(password);
        Label label = new Label(labelText);
        label.setStyle("-fx-font: 14px Poppins");
        getChildren().addAll(label, textInput);
    }
}
