/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.passly.app.Components;

import java.sql.Blob;
import java.sql.SQLException;
import javafx.scene.image.ImageView;
import javafx.scene.image.Image;
import javafx.scene.shape.Circle;

/**
 *
 * @author bryan
 */
public class Picture extends ImageView {

    private Picture(Image image, int size) {
        super(image);
        setFitHeight(size);
        setFitWidth(size);
    }

    public static Picture create(Blob imageFile, int size) {
        try {
            Image image = new Image(imageFile.getBinaryStream());

            return new Picture(image, size);
        } catch (SQLException e) {
            System.out.println("Failed to create image: " + e.getMessage());
            return null;
        }
    }

    public static Picture createRounded(Blob imageFile, int size) {
        try {
            Image image = new Image(imageFile.getBinaryStream());
            Picture picture = new Picture(image, size);
            Circle clip = new Circle(size / 2);
            clip.setCenterX(size / 2);
            clip.setCenterY(size / 2);
            picture.setClip(clip);
            return picture;
        } catch (SQLException e) {
            System.out.println("Failed to create image: " + e.getMessage());
            return null;
        }
    }
}
