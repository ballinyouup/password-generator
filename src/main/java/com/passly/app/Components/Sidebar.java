/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.passly.app.Components;

import atlantafx.base.controls.Card;
import com.passly.app.Context;
import com.passly.app.Route;
import java.sql.Blob;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.Border;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import static javafx.scene.layout.Region.USE_COMPUTED_SIZE;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

/**
 *
 * @author bryan
 */
public class Sidebar extends Card {

    public Sidebar() {
        // Styles
        setMaxWidth(240);
        setMinWidth(240);
        setPrefWidth(USE_COMPUTED_SIZE);
        setMaxHeight(Double.MAX_VALUE);
        setBorder(Context.DEBUG ? Border.stroke(Color.WHITE) : null);
        getStyleClass().add("sidebar");
        SidebarHeader header = new SidebarHeader();
        SidebarBody body = new SidebarBody();
        VBox.setVgrow(body, Priority.ALWAYS);

        SidebarFooter footer = new SidebarFooter();
        setHeader(header);
        setBody(body);
        setFooter(footer);
    }

    private class SidebarHeader extends VBox {

        public SidebarHeader() {
            setBorder(Context.DEBUG ? Border.stroke(Color.WHITE) : null);
            setPrefWidth(USE_COMPUTED_SIZE);
            setMaxWidth(Double.MAX_VALUE);
            setMinHeight(64);

            GUILogger logger = new GUILogger(Context.getUser().getFields(), Context.getUser().getImageProperty());
            Label companyName = new Label("PASSLY");
            companyName.getStyleClass().add("sidebar-header");
            getChildren().addAll(logger, companyName);
        }

    }

    private class SidebarBody extends VBox {

        public SidebarBody() {
            setPrefWidth(USE_COMPUTED_SIZE);
            setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
            setBorder(Context.DEBUG ? Border.stroke(Color.WHITE) : null);
            getChildren().add(new Link(Route.LOGIN, "Logout"));
        }

    }

    private class SidebarFooter extends HBox {

        public SidebarFooter() {
            setPrefWidth(USE_COMPUTED_SIZE);
            setMaxWidth(Double.MAX_VALUE);
            setSpacing(8.0);
            setAlignment(Pos.CENTER_LEFT);
            setBorder(Context.DEBUG ? Border.stroke(Color.WHITE) : null);

            if (Context.getUser().getImageProperty().isNotNull().get()) {
                Blob blob = (Blob) Context.getUser().getImageProperty().getValue();
                Picture image = Picture.createRounded(blob, 40);
                getChildren().add(image);
            }

            VBox textContainer = new VBox();
            Label name = new Label(Context.getUser().getFullName());
            name.setMaxWidth(Double.MAX_VALUE);
            HBox.setHgrow(name, Priority.ALWAYS);
            Label username = new Label("@" + Context.getUser().getUsername());
            name.setMaxWidth(Double.MAX_VALUE);
            HBox.setHgrow(username, Priority.ALWAYS);

            textContainer.getChildren().addAll(name, username);
            getChildren().addAll(textContainer);
        }

    }

}
