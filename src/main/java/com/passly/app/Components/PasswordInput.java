/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.passly.app.Components;

import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;

/**
 *
 * @author bryan
 */
public class PasswordInput extends PasswordField {

    Label label;

    public PasswordInput(SimpleStringProperty password, String labelText) {
        textProperty().bindBidirectional(password);
        label = new Label(labelText);
        label.setStyle("-fx-font: 14px Poppins");
    }

    public Label getLabel() {
        return this.label;
    }
}
