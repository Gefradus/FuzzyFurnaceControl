package IOSets;

import javafx.scene.control.ScrollPane;


public class IOSetsScrollPane extends ScrollPane
{
    public IOSetsScrollPane()
    {
        IOSetFlowPane flow = new IOSetFlowPane();
        setHbarPolicy(ScrollBarPolicy.AS_NEEDED);    // Horizontal scroll bar
        setVbarPolicy(ScrollBarPolicy.AS_NEEDED);    // Vertical scroll bar
        setContent(flow);
        viewportBoundsProperty().addListener((ov, oldBounds, bounds) ->
        {
            flow.setPrefWidth(bounds.getWidth());
            flow.setPrefHeight(bounds.getHeight());
        });
    }
}
