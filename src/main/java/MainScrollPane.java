import javafx.scene.control.ScrollPane;

public class MainScrollPane extends ScrollPane
{
    public MainScrollPane(MainFlowPane flow) {
        setHbarPolicy(ScrollBarPolicy.AS_NEEDED);    // Horizontal scroll bar
        setVbarPolicy(ScrollBarPolicy.ALWAYS);    // Vertical scroll bar
        setContent(flow);
        viewportBoundsProperty().addListener((ov, oldBounds, bounds) ->
        {
            flow.setPrefWidth(bounds.getWidth());
            flow.setPrefHeight(bounds.getHeight());
        });
        setStyle("-fx-background-color: #333333;");

    }
}
