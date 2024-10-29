/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.passly.app.Components;

import com.passly.app.Context;
import com.passly.app.Router;
import java.util.Map;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

/**
 *
 * @author bryan
 */
public class GUILogger extends VBox {

    public GUILogger(Map<String, SimpleStringProperty> list, SimpleObjectProperty... obj) {

        ObservableList<Node> nodes = FXCollections.observableArrayList();
        Label routeText = logLabel(Router.getCurrentRouteProperty());
        nodes.add(routeText);
        for (SimpleStringProperty field : list.values()) {
            System.out.println(field);
            Label fieldLabel = logLabel(field);
            nodes.add(fieldLabel);
        }

        for (SimpleObjectProperty item : obj) {
            System.out.println(item.getName() + " " + item.getValue());

            Label label = new Label();
            label.setText(item.getName() + ": " + (item.getValue() == null ? "" : item.getValue()));

            item.addListener((_, _, value) -> {
                String updatedText = item.getName() + ": " + (value == null ? "" : value);
                label.setText(updatedText);
            });
            nodes.add(label);
        }
        setAlignment(Pos.TOP_LEFT);
        if (Context.GUI_DEBUG) {
            getChildren().addAll(nodes);
        }

    }

    private Label logLabel(SimpleStringProperty field) {
        Label label = new Label();
        label.setText(field.getName() + ": " + (field.getValue() == null ? "" : field.getValue()));

        field.addListener((_, _, value) -> {
            String updatedText = field.getName() + ": " + (value == null ? "" : value);
            label.setText(updatedText);
        });

        return label;
    }

}
