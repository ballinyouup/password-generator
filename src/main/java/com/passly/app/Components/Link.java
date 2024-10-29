package com.passly.app.Components;

import com.passly.app.Route;
import com.passly.app.Router;
import javafx.geometry.Pos;
import javafx.scene.control.Label;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author bryan
 */
public class Link extends Label {

    public Link(Route href, String text) {
        setText(text);
        setAlignment(Pos.CENTER);
        getStyleClass().add("link");
        // Event Handlers for Register
//        setOnMouseEntered(e -> {
//            setUnderline(true);
//            setCursor(Cursor.HAND);
//        });
//        setOnMouseExited(e -> {
//            setUnderline(false);
//            setCursor(Cursor.DEFAULT);
//        });
        setOnMouseClicked(e -> {
            Router.push(href);
        });
    }
}
