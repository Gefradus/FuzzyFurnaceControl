package main.mainPanes;

import main.Main;
import panes.BasicFlowPane;

public class MainFlowPane extends BasicFlowPane
{
    public MainFlowPane(Main main){
        getChildren().addAll(new MainGridPane(main));
        setStyle("-fx-background-color: #333333;");
    }
}
