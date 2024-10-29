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
        setHeader(cardHeader);
        setBody(new PasswordGeneratorCardBody());
        setFooter(new PasswordGeneratorCardFooter());
    }

    private class PasswordGeneratorCardBody extends VBox {

        public PasswordGeneratorCardBody() {
            setStyles();
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
            getChildren().addAll(new GeneratedPasswordText(), lengthLabel, new PasswordSlider(0, 24, 16, 4, 12, 4));
        }

        private class GeneratedPasswordText extends TextArea {

            public GeneratedPasswordText() {
                textProperty().bind(generatedPassword);
                generatedPassword.set(new Password(length.getValue(), true, true, true).getPassword());
                setStyle(
                        """
                    -fx-font-family: Poppins;
                    -fx-font-size: 24px;
                    -fx-font-weight: 900;
                    """
                );
            }
        }

        private class PasswordSlider extends Slider {

            public PasswordSlider(int min, int max, int defaultValue, int major, int block, int minor) {
                super(min, max, defaultValue);
                init(major, block, minor);
                setStyles();

            }

            private void init(int major, int block, int minor) {
                setShowTickLabels(true);
                setShowTickMarks(true);
                setMajorTickUnit(major);
                setBlockIncrement(block);
                setMinorTickCount(minor);
                setSnapToTicks(true);
                valueProperty().bindBidirectional(length);

            }

            private void setStyles() {
                getStyleClass().add(Styles.LARGE);
                setSkin(new ProgressSliderSkin(this));
            }

        }

        private void setStyles() {
            setBorder(Context.DEBUG ? Border.stroke(Color.WHITE) : null);
            setMaxHeight(40);
            setMaxWidth(520);

        }

    }

    private class PasswordGeneratorCardFooter extends HBox {

        public PasswordGeneratorCardFooter() {
            setStyles();

            getChildren().addAll(new GenerateButton(), new SaveButton());
        }

        private void setStyles() {
            setSpacing(8.0);
            setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
            setBorder(Context.DEBUG ? Border.stroke(Color.WHITE) : null);
            setAlignment(Pos.CENTER_LEFT);
        }

        private class GenerateButton extends Button {

            public GenerateButton() {
                setText("GENERATE");
                setStyles();
                setHandlers();
            }

            private void setStyles() {
                getStyleClass().addAll(Styles.LARGE);
            }

            private void setHandlers() {
                setOnMouseClicked(_ -> {
                    generatedPassword.set(new Password(length.getValue(), true, true, true).getPassword());
                });
            }
        }

        private class SaveButton extends Button {

            public SaveButton() {
                setText("SAVE");
                setStyles();
                setHandlers();

            }

            private void setStyles() {
                getStyleClass().add(Styles.LARGE);

            }

            private void setHandlers() {
                setOnMouseClicked(e -> {
                    Context.getUser().addPassword(new Password(generatedPassword.getValue()));
                    generatedPassword.set(new Password(length.getValue(), true, true, true).getPassword());
                });
            }
        }
    }
}
