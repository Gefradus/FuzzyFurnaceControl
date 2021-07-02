package main;

import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class InfoButton extends Button {
    InfoButton() {
        setGraphic(new ImageView(new Image(getClass().getResourceAsStream("/img/info.png"))));
        setStyle("-fx-background-color: transparent; -fx-cursor: hand;");
    }
}
