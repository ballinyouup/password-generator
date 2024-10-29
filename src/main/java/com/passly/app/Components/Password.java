/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.passly.app.Components;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;
import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author bryan
 */
public class Password {

    private static final String ALPHABET = "abcdefghijklmnopqrstuvwxyz";
    private static final String SYMBOLS = "`~!@#$%^&*()_=+{};:',.?/";
    private static final String NUMBERS = "0123456789";
    private static final String UPPERCASE = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final SecureRandom random = new SecureRandom();
    private static final ArrayList<String> list = new ArrayList<>(List.of(ALPHABET, SYMBOLS, NUMBERS, UPPERCASE));
    private final SimpleStringProperty password = new SimpleStringProperty(this, "Password", "");

    public Password(int length, boolean includeSymbols, boolean includeNumbers, boolean includeUpperCase) {
        generate(length, includeSymbols, includeNumbers, includeUpperCase);
    }

    public Password(String password) {
        this.password.set(password);
    }

    public SimpleStringProperty getPasswordProperty() {
        return password;
    }

    public String getPassword() {
        return password.getValue();
    }

    private void generate(int length, boolean includeSymbols, boolean includeNumbers, boolean includeUpperCase) {
        if (!includeSymbols) {
            list.remove(SYMBOLS);
        }
        if (!includeNumbers) {
            list.remove(NUMBERS);

        }
        if (!includeUpperCase) {
            list.remove(UPPERCASE);
        }
        for (int i = 0; i < length; i++) {
            int randomSet = random.nextInt(0, list.size());
            int randomNumber = random.nextInt(0, list.get(randomSet).length());
            password.setValue(password.getValue() + list.get(randomSet).charAt(randomNumber));
        }

    }
}
