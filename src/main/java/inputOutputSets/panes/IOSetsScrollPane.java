package inputOutputSets.panes;

import panes.BasicScrollPane;

public class IOSetsScrollPane extends BasicScrollPane
{
    public IOSetsScrollPane() {
        super(new IOSetFlowPane());
    }
}
