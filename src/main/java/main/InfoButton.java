package main;

import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.Objects;

public class InfoButton extends Button {
    InfoButton() {
        setGraphic(new ImageView(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/img/info.png")))));
        setStyle("-fx-background-color: transparent; -fx-cursor: hand;");
    }
}