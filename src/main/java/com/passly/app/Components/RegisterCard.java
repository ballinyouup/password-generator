/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.passly.app.Components;

/**
 *
 * @author bryan
 */
import atlantafx.base.controls.Card;
import atlantafx.base.theme.Styles;
import atlantafx.base.theme.Tweaks;
import com.passly.app.Route;
import com.passly.app.Services.User.UserService;
import java.io.File;
import java.io.InputStream;
import java.util.LinkedHashMap;
import java.util.Map;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;

public class RegisterCard extends Card {

    private final double WIDTH = 640;
    private final double HEIGHT = 480;
    private final SimpleStringProperty fullName;
    private final SimpleStringProperty username;
    private final SimpleStringProperty password;
    private final SimpleStringProperty verifyPassword;
    private final SimpleStringProperty phoneNumber;
    private final SimpleStringProperty gender;
    private final SimpleObjectProperty<InputStream> image;
    private final SimpleStringProperty imagePath;

    public RegisterCard() {
        fullName = new SimpleStringProperty(this, "Full Name", "");
        username = new SimpleStringProperty(this, "Username", "");
        password = new SimpleStringProperty(this, "Password", "");
        verifyPassword = new SimpleStringProperty(this, "Verify Password", "");
        phoneNumber = new SimpleStringProperty(this, "Phone Number", "");
        gender = new SimpleStringProperty(this, "Gender", "");
        image = new SimpleObjectProperty<>(this, "Image", null);
        imagePath = new SimpleStringProperty(this, "Image Path", "");

        CardHeader cardHeader = new CardHeader("Register to Nebula", "Fill out the form to create an account");
        setHeader(cardHeader);
        setBody(new RegisterCardBody());
        setFooter(createFooter());
        setMaxSize(WIDTH, HEIGHT);
    }

    private class RegisterCardBody extends VBox {

        public RegisterCardBody() {
            setStyles();
            setChildren();
        }

        private void setStyles() {
            setSpacing(8.0);
            setChildren();
        }

        private void setChildren() {
            TextInput fullNameInput = new TextInput(fullName, "Full Name");
            TextInput usernameInput = new TextInput(username, "Username");
            PasswordInput passwordInput = new PasswordInput(password, "Password");
            PasswordInput verifyPasswordInput = new PasswordInput(verifyPassword, "Verify Password");
            TextInput phoneNumberInput = new TextInput(phoneNumber, "Phone Number");

            Label comboBoxLabel = new Label("Gender");
            ComboBox comboBox = new ComboBox<String>();
            comboBox.getStyleClass().add(Tweaks.ALT_ICON);
            comboBox.setMaxSize(WIDTH, HEIGHT);
            comboBox.setItems(FXCollections.observableArrayList(
                    "Male", "Female"
            ));
            comboBox.getSelectionModel().selectFirst();
            gender.set(comboBox.getSelectionModel().getSelectedItem().toString());
            comboBox.getSelectionModel().selectedItemProperty().addListener(e -> {
                System.out.println("Before update gender: " + gender.getValue());
                gender.set(comboBox.getSelectionModel().getSelectedItem().toString());
                System.out.println("After update gender: " + gender.getValue());
            });

            Button uploadImageButton = new Button("Upload Profile Image");
            uploadImageButton.setOnAction(e -> handleFileInput());
            Label pathLabel = new Label("Select an Image ");
            imagePath.addListener((_) -> {
                pathLabel.setText("Selected Image: " + imagePath.getValue());
            });
            getChildren().addAll(fullNameInput, usernameInput, passwordInput, verifyPasswordInput, phoneNumberInput, comboBoxLabel, comboBox, pathLabel, uploadImageButton);
        }

    }

    private VBox createFooter() {
        VBox cardFooter = new VBox();
        cardFooter.getStyleClass().add("card-footer");
        cardFooter.setSpacing(28.0);

        Button registerButton = new Button("Register");
        registerButton.getStyleClass().add(Styles.ACCENT);
        registerButton.setStyle("-fx-font: 16px Poppins");
        registerButton.setMaxSize(WIDTH, HEIGHT);

        registerButton.setOnMouseClicked(e -> UserService.registerUser(getFields(), image));

        Link loginLink = new Link(Route.LOGIN, "Already have an account? Click here to login!");
        cardFooter.getChildren().addAll(registerButton, loginLink);

        return cardFooter;
    }

    public void handleFileInput() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select Profile Image");
        fileChooser.getExtensionFilters().add(
                new FileChooser.ExtensionFilter("Image Files", "*.jpg", "*.jpeg", "*.png"));

        File selectedFile = fileChooser.showOpenDialog(null);
        if (selectedFile != null) {
            imagePath.set(selectedFile.getAbsolutePath());
        }
    }

    public Map<String, SimpleStringProperty> getFields() {
        Map<String, SimpleStringProperty> list = new LinkedHashMap<>();
        list.put(fullName.getName(), fullName);
        list.put(username.getName(), username);
        list.put(password.getName(), password);
        list.put(verifyPassword.getName(), verifyPassword);
        list.put(phoneNumber.getName(), phoneNumber);
        list.put(gender.getName(), gender);
        list.put(imagePath.getName(), imagePath);

        return list;
    }
}
