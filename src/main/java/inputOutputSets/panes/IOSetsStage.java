package inputOutputSets.panes;

import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Screen;
import javafx.stage.Stage;


public class IOSetsStage extends Stage
{
    public IOSetsStage() {
        StackPane root = new StackPane();
        root.getChildren().addAll(new IOSetsScrollPane());
        setScene(new Scene(root));
        show();
        setWidth(800);
        setHeight(responsiveHeight());
        setResizable(false);
    }

    private int responsiveHeight()
    {
        double screenHeight = Screen.getPrimary().getBounds().getHeight();

        if ( screenHeight < 768) {
            return 600;
        }
        else if (screenHeight < 900) {
            return 768;
        }
        else if (screenHeight < 1080){
            return 900;
        }
        else {
            return 1020;
        }
    }

}