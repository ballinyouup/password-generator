/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.passly.app.Components;

import atlantafx.base.controls.Card;
import atlantafx.base.theme.Styles;
import com.passly.app.Context;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;
import javafx.beans.property.SimpleStringProperty;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.Border;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;

/**
 *
 * @author bryan
 */
public class PasswordGeneratorCard extends Card {

    private final SimpleStringProperty generatedPassword = new SimpleStringProperty(this, "Generated Password", "");

    public PasswordGeneratorCard() {
        CardHeader cardHeader = new CardHeader("Password Generator", "Generate a new password based on settings");
        cardHeader.setBorder(Context.DEBUG ? Border.stroke(Color.WHITE) : null);

        var body = new PasswordGeneratorCardBody();
        var footer = new PasswordGeneratorCardFooter();
//        HBox.setHgrow(footer, Priority.ALWAYS);
//        VBox.setVgrow(footer, Priority.ALWAYS);

//        setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        setHeader(cardHeader);
        setBody(body);
        setFooter(footer);
    }

    private class PasswordGeneratorCardBody extends HBox {

        public PasswordGeneratorCardBody() {
            setBorder(Context.DEBUG ? Border.stroke(Color.WHITE) : null);
            setMaxHeight(40);
            TextArea generatedPasswordLabel = new TextArea();
            setMaxWidth(320);
            generatedPasswordLabel.textProperty().bind(generatedPassword);
            generatedPassword.set(Password.generatePassword(12));
            generatedPasswordLabel.setStyle(
                    """
                    -fx-font-family: Poppins;
                    -fx-font-size: 24px;
                    -fx-font-weight: 900;
                    """
            );

            getChildren().add(generatedPasswordLabel);
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
                generatedPassword.set(Password.generatePassword(12));
            });
            Button saveButton = new Button("SAVE");

            saveButton.getStyleClass().add(Styles.LARGE);

            getChildren().addAll(generateButton, saveButton);
        }
    }

    private class Password {

        private static final String ALPHABET = "abcdefghijklmnopqrstuvwxyz";
        private static final String SYMBOLS = "`~!@#$%^&*()_=+{};:',.?/";
        private static final String NUMBERS = "0123456789";
        private static final String UPPERCASE = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        private static final SecureRandom random = new SecureRandom();
        private static final ArrayList<String> list = new ArrayList<>(List.of(ALPHABET, SYMBOLS, NUMBERS, UPPERCASE));

        public static String generatePassword(int length) {
            StringBuilder password = new StringBuilder();
            for (int i = 0; i < length; i++) {
                int randomSet = random.nextInt(0, 4);
                int randomNumber = random.nextInt(0, list.get(randomSet).length());
                password.append(list.get(randomSet).charAt(randomNumber));
            }
            return password.toString();
        }
    }
}
