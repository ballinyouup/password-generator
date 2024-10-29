/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.passly.app;

/**
 *
 * @author bryan
 */
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Supplier;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Router extends Node {

    private static final Router instance = new Router();
    private static final SimpleStringProperty currentRouteProperty = new SimpleStringProperty(Router.instance, "Current Route", "");
    private static Stage stage = new Stage();
    private static final Map<Route, Supplier<Parent>> routes = new LinkedHashMap<>();

    private Router() {
    }

    public static Stage getStage() {
        return stage;
    }

    public static void setStage(Stage stage) {
        Router.stage = stage;
    }

    public static SimpleStringProperty getCurrentRouteProperty() {
        return Router.currentRouteProperty;
    }

    public static void addRoute(Route route, Supplier<Parent> parent) {
        routes.put(route, parent);
    }

    public static void push(Route route) {
        // Create a new instance every time we navigate
        Parent newRoot = routes.get(route).get();

        if (Router.stage.getScene() == null) {
            Scene scene = new Scene(newRoot, App.WINDOW_WIDTH, App.WINDOW_HEIGHT);
            scene.getStylesheets().add(instance.getClass().getResource("css/styles.css").toExternalForm());
            Router.stage.setScene(scene);
        } else {
            Router.stage.getScene().setRoot(newRoot);
        }

        Router.currentRouteProperty.set(route.getPath());
        String title = route.getPath().replace("/", "");
        Router.stage.setTitle(title.substring(0, 1).toUpperCase() + title.substring(1));
    }
}
