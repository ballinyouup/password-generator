/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.passly.app.Models;

import com.passly.app.Components.Password;
import com.passly.app.Services.SQL.SQL;
import java.sql.Blob;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author bryan
 */
public class User {

    private final SimpleStringProperty fullName;
    private final SimpleStringProperty username;
    private final SimpleStringProperty phoneNumber;
    private final SimpleStringProperty gender;
    private final SimpleObjectProperty image;
    private final ObservableList<Password> passwords = FXCollections.observableArrayList();

    public User() {
        this.fullName = new SimpleStringProperty(this, "Full Name", "");
        this.username = new SimpleStringProperty(this, "Username", "");
        this.phoneNumber = new SimpleStringProperty(this, "Phone Number", "");
        this.gender = new SimpleStringProperty(this, "Gender", "");
        this.image = new SimpleObjectProperty(this, "Image", null);
    }

    public User(String fullName, String username, String phoneNumber, String gender, Blob image) {
        this.fullName = new SimpleStringProperty(this, "Full Name", fullName);
        this.username = new SimpleStringProperty(this, "Username", username);
        this.phoneNumber = new SimpleStringProperty(this, "Phone Number", phoneNumber);
        this.gender = new SimpleStringProperty(this, "Gender", gender);
        this.image = new SimpleObjectProperty(this, "Image", image);
    }

    public static User fromResultSet(ResultSet rs) throws SQLException {
        return new User(
                rs.getString(SQL.Table.Column.FULL_NAME.get()),
                rs.getString(SQL.Table.Column.USERNAME.get()),
                rs.getString(SQL.Table.Column.PHONE.get()),
                rs.getString(SQL.Table.Column.GENDER.get()),
                rs.getBlob(SQL.Table.Column.PICTURE.get()));
    }

    public String getFullName() {
        return this.fullName.getValue();
    }

    public String getUsername() {
        return this.username.getValue();
    }

    public String getPhoneNumber() {
        return this.phoneNumber.getValue();
    }

    public String getGender() {
        return this.gender.getValue();
    }

    public SimpleObjectProperty getImageProperty() {
        return this.image;
    }

    public void addPassword(Password password) {
        passwords.add(password);
    }

    public ObservableList<Password> getPasswords() {
        return passwords;
    }

    public Map<String, SimpleStringProperty> getFields() {
        Map<String, SimpleStringProperty> fields = new LinkedHashMap();
        fields.put(this.fullName.getName(), this.fullName);
        fields.put(this.username.getName(), this.username);
        fields.put(this.phoneNumber.getName(), this.phoneNumber);
        fields.put(this.gender.getName(), this.gender);
//        fields.put("Image", this.image);
        return fields;
    }
}
