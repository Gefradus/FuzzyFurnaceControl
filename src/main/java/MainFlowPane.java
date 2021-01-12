import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.FlowPane;

public class MainFlowPane extends FlowPane {
    public MainFlowPane(MainGridPane gridPane){
        setPadding(new Insets(5, 5, 5, 5));
        setVgap(5);
        setHgap(5);
        setAlignment(Pos.CENTER);

        getChildren().addAll(gridPane);
        setStyle("-fx-background-color: #333333;");
    }
}
