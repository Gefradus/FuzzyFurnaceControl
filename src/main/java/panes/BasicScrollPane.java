package panes;

import javafx.scene.control.ScrollPane;
import javafx.scene.layout.FlowPane;

public class BasicScrollPane extends ScrollPane
{
    public BasicScrollPane(FlowPane flow)
    {
        setHbarPolicy(ScrollBarPolicy.AS_NEEDED);
        setVbarPolicy(ScrollBarPolicy.ALWAYS);
        setContent(flow);
        viewportBoundsProperty().addListener((ov, oldBounds, bounds) ->
        {
            flow.setPrefWidth(bounds.getWidth());
            flow.setPrefHeight(bounds.getHeight());
        });
    }
}
