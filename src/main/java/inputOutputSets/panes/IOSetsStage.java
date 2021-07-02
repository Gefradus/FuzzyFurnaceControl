package inputOutputSets.panes;

import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import static javafx.stage.Screen.getPrimary;


public class IOSetsStage extends Stage
{
    private static boolean visible;

    private IOSetsStage() {
        StackPane root = new StackPane();
        root.getChildren().addAll(new IOSetsScrollPane());
        setScene(new Scene(root));
        show();
        setWidth(800);
        setHeight(responsiveHeight());
        setResizable(false);
    }

    public static void init() {
        if(!visible) {
            new IOSetsStage().setOnCloseRequest(event -> visible = false);
            visible = true;
        }
    }

    private int responsiveHeight()
    {
        double screenHeight = getPrimary().getBounds().getHeight();

        if (screenHeight < 768) {
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