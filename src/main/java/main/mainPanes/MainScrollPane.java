package main.mainPanes;

import main.Main;
import panes.BasicScrollPane;

public class MainScrollPane extends BasicScrollPane
{
    public MainScrollPane(Main main) {
        super(new MainFlowPane(main));
        setStyle("-fx-background-color: #333333;");
    }
}
