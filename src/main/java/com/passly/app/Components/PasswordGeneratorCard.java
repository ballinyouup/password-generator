/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.passly.app.Components;

import atlantafx.base.controls.Card;
import atlantafx.base.controls.ProgressSliderSkin;
import atlantafx.base.theme.Styles;
import com.passly.app.Context;
import com.passly.app.Route;
import com.passly.app.Router;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextArea;
import javafx.scene.layout.Border;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

/**
 *
 * @author bryan
 */
public class PasswordGeneratorCard extends Card {

    private final SimpleStringProperty generatedPassword = new SimpleStringProperty(this, "Generated Password", "");
    private final SimpleIntegerProperty length = new SimpleIntegerProperty(this, "Password Length", 16);

    public PasswordGeneratorCard() {
        CardHeader cardHeader = new CardHeader("Password Generator", "Generate a new password based on settings");
        cardHeader.setBorder(Context.DEBUG ? Border.stroke(Color.WHITE) : null);

        var body = new PasswordGeneratorCardBody();
        var footer = new PasswordGeneratorCardFooter();
        setHeader(cardHeader);
        setBody(body);
        setFooter(footer);
    }

    private class PasswordGeneratorCardBody extends VBox {

        public PasswordGeneratorCardBody() {
            setBorder(Context.DEBUG ? Border.stroke(Color.WHITE) : null);
            setMaxHeight(40);
            TextArea generatedPasswordLabel = new TextArea();
            setMaxWidth(520);
            generatedPasswordLabel.textProperty().bind(generatedPassword);
            generatedPassword.set(new Password(length.getValue(), true, true, true).getPassword());
            generatedPasswordLabel.setStyle(
                    """
                    -fx-font-family: Poppins;
                    -fx-font-size: 24px;
                    -fx-font-weight: 900;
                    """
            );

            var lengthSlider = createTickSlider();
            lengthSlider.valueProperty().bindBidirectional(length);
            lengthSlider.getStyleClass().add(Styles.LARGE);
            lengthSlider.setSkin(new ProgressSliderSkin(lengthSlider));
            Label lengthLabel = new Label("Length: " + length.getValue());
            lengthLabel.setStyle(
                    """
                    -fx-font-family: Poppins;
                    -fx-font-size: 16px;
                    -fx-font-weight: 400;
                    """
            );
            length.addListener(e -> {
                lengthLabel.setText("Length: " + length.getValue().toString());
            });
            getChildren().addAll(generatedPasswordLabel, lengthLabel, lengthSlider);
        }
    }

    private class PasswordGeneratorCardFooter extends HBox {

        public PasswordGeneratorCardFooter() {
            setSpacing(8.0);
            setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
            setBorder(Context.DEBUG ? Border.stroke(Color.WHITE) : null);
            setAlignment(Pos.CENTER_LEFT);

            Button generateButton = new Button("GENERATE");
            generateButton.getStyleClass().addAll(Styles.LARGE);
            generateButton.setOnMouseClicked(_ -> {
                generatedPassword.set(new Password(length.getValue(), true, true, true).getPassword());
            });
            Button saveButton = new Button("SAVE");
            saveButton.setOnMouseClicked(e -> {
                Context.getUser().addPassword(new Password(generatedPassword.getValue()));
                generatedPassword.set(new Password(length.getValue(), true, true, true).getPassword());
            });

            saveButton.getStyleClass().add(Styles.LARGE);

            getChildren().addAll(generateButton, saveButton);
        }
    }

    private Slider createTickSlider() {
        var slider = new Slider(8, 24, 16);
        slider.setShowTickLabels(true);
        slider.setShowTickMarks(true);
        slider.setMajorTickUnit(4);
        slider.setBlockIncrement(12);
        slider.setMinorTickCount(4);
        slider.setSnapToTicks(true);
        return slider;
    }
}
