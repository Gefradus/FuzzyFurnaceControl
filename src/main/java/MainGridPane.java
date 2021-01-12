import javafx.geometry.Insets;
import javafx.scene.layout.GridPane;


public class MainGridPane extends GridPane{

    public MainGridPane(Main main)
    {
        getChildren().addAll(main.getHeader(), main.getAreaLabel(), main.getHeightLabel(), main.getIsolationLabel(), main.getPowerLabel(),
                main.getStartTempLabel(),main.getOptTempLabel(), main.getBreakTimeLabel(), main.getSeasonLabel(), main.getAreaField(),
                main.getHeightField(), main.getPowerField(), main.getStartTempField(), main.getOptTempField(), main.getIsolationChoiceBox(),
                main.getSeasonChoiceBox(), main.getBreakTimeChoiceBox(), main.getStartSimulation(), main.getShowInAndOutSets(), main.getInfoIsolation(),
                main.getInfoSeason(), main.getInfoBreakTime());

        setConstraints(main.getHeader(),0,0,3,1);

        main.getHeader().setPadding(new Insets(0,0,10,0));

        setConstraints(main.getAreaLabel(), 1,1);
        setConstraints(main.getAreaField(), 1,2);

        setConstraints(main.getHeightLabel(), 1,3);
        setConstraints(main.getHeightField(),1,4);

        setConstraints(main.getPowerLabel(),1,5);
        setConstraints(main.getPowerField(),1,6);

        setConstraints(main.getStartTempLabel(),1,7);
        setConstraints(main.getStartTempField(),1,8);

        setConstraints(main.getOptTempLabel(),1,9);
        setConstraints(main.getOptTempField(),1,10);

        setConstraints(main.getIsolationLabel(),1,11);
        setConstraints(main.getIsolationChoiceBox(),1,12);
        setConstraints(main.getInfoIsolation(),2,12);

        setConstraints(main.getSeasonLabel(), 1,13);
        setConstraints(main.getSeasonChoiceBox(),1,14);
        setConstraints(main.getInfoSeason(), 2, 14);

        setConstraints(main.getBreakTimeLabel(), 1, 15);
        setConstraints(main.getBreakTimeChoiceBox(), 1, 16);
        setConstraints(main.getInfoBreakTime(), 2, 16);

        setConstraints(main.getStartSimulation(),3,8);
        setConstraints(main.getShowInAndOutSets(), 3, 10);

        setPadding(new Insets(10, 0, 20, 20));
        setVgap(5);
        setStyle("-fx-background-color: #333333;");
    }
}
