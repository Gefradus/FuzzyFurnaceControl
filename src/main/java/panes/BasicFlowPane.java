package panes;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.FlowPane;

public class BasicFlowPane extends FlowPane {
    public BasicFlowPane() {
        setPadding(new Insets(5, 5, 5, 5));
        setVgap(5);
        setHgap(5);
        setAlignment(Pos.CENTER);
    }
}
