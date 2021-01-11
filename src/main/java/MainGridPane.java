import javafx.geometry.Insets;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;

public class MainGridPane extends GridPane{

    public MainGridPane(Main main)
    {
        getChildren().addAll(main.getHeader(), main.getAreaLabel(), main.getHeightLabel(), main.getIsolationLabel(), main.getPowerLabel(),
                main.getStartTempLabel(),main.getOptTempLabel(), main.getBreakTimeLabel(), main.getSeasonLabel(), main.getAreaField(),
                main.getHeightField(), main.getPowerField(), main.getStartTempField(), main.getOptTempField(), main.getIsolationChoiceBox(),
                main.getSeasonChoiceBox(), main.getBreakTimeChoiceBox(), main.getStartSimulation(), main.getShowInAndOutSets(), main.getInfoIsolation(),
                main.getInfoSeason(), main.getInfoBreakTime());

        setConstraints(main.getHeader(),0,0,3,1);
        setConstraints(main.getAreaLabel(), 0,1);
        setConstraints(main.getHeightLabel(), 0,2);
        setConstraints(main.getPowerLabel(),0,3);
        setConstraints(main.getStartTempLabel(),0,4);
        setConstraints(main.getOptTempLabel(),0,5);
        setConstraints(main.getIsolationLabel(),0,6);
        setConstraints(main.getSeasonLabel(), 0,7);
        setConstraints(main.getBreakTimeLabel(), 0, 8);

        setConstraints(main.getInfoIsolation(),2,6);
        setConstraints(main.getInfoSeason(), 2, 7);
        setConstraints(main.getInfoBreakTime(), 2, 8);
        setConstraints(main.getShowInAndOutSets(), 0, 9);

        setConstraints(main.getAreaField(), 1,1);
        setConstraints(main.getHeightField(),1,2);
        setConstraints(main.getPowerField(),1,3);
        setConstraints(main.getStartTempField(),1,4);
        setConstraints(main.getOptTempField(),1,5);
        setConstraints(main.getIsolationChoiceBox(),1,6);
        setConstraints(main.getSeasonChoiceBox(),1,7);
        setConstraints(main.getBreakTimeChoiceBox(), 1, 8);
        setConstraints(main.getStartSimulation(),1,9);
        setConstraints(main.getShowInAndOutSets(), 1, 10);

        for(int i=0; i<9; i++) {
            getRowConstraints().add(new RowConstraints(40));
        }
        getRowConstraints().add(new RowConstraints(65));
        getRowConstraints().add(new RowConstraints(50));

        setPadding(new Insets(10, 0, 0, 100));
        setVgap(5);    // przerwy miedzy wierszami
        setStyle("-fx-background-color: #333333;");
    }
}
