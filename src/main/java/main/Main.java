package main;

import inputOutputSets.panes.IOSetsStage;
import alerts.InfoAlert;
import javafx.application.Application;
import javafx.scene.control.*;
import javafx.stage.Stage;
import lombok.Getter;
import lombok.Setter;

public class Main extends Application {

    @Getter @Setter
    private Button infoBreakTime, startSimulation, infoIsolation, infoSeason, showInAndOutSets;
    @Getter @Setter
    private ChoiceBox<String> isolationChoiceBox, seasonChoiceBox, breakTimeChoiceBox;
    @Getter @Setter
    private Label header, areaLabel, heightLabel, powerLabel, startTempLabel, optTempLabel, isolationLabel, seasonLabel, breakTimeLabel;
    @Getter @Setter
    private TextField areaField, heightField, powerField, startTempField, optTempField;
    private boolean ioSetsStageVisible;

    @Override
    public void start(Stage window) {
        new StageItems(this, window);

        infoIsolation.setOnAction(e -> new InfoAlert("infoIsolation"));
        infoSeason.setOnAction(e -> new InfoAlert("infoSeason"));
        infoBreakTime.setOnAction(e -> new InfoAlert("infoBreakTime"));
        startSimulation.setOnAction(e -> new Simulation(this));

        showInAndOutSets.setOnAction(e -> {
            if(!ioSetsStageVisible){
                new IOSetsStage().setOnCloseRequest(event -> ioSetsStageVisible = false);
                ioSetsStageVisible = true;
            }
        });
    }

}


