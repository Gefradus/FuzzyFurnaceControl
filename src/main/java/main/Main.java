package main;

import inputOutputSets.panes.IOSetsStage;
import alerts.InfoAlert;
import javafx.application.Application;
import javafx.scene.control.*;
import javafx.stage.Stage;
import lombok.Getter;
import lombok.Setter;

import static main.InfoAlertType.*;

public class Main extends Application {

    @Getter @Setter
    private InfoButton infoBreakTime, infoIsolation, infoSeason;
    @Getter @Setter
    private Button startSimulation, showInAndOutSets;
    @Getter @Setter
    private ChoiceBox<String> isolationChoiceBox, seasonChoiceBox, breakTimeChoiceBox;
    @Getter @Setter
    private Label header, areaLabel, heightLabel, powerLabel, startTempLabel, optTempLabel, isolationLabel, seasonLabel, breakTimeLabel;
    @Getter @Setter
    private TextField areaField, heightField, powerField, startTempField, optTempField;


    @Override
    public void start(Stage window) {
        StageItems.init(this, window);
        infoIsolation.setOnAction(e -> new InfoAlert(ISOLATION));
        infoSeason.setOnAction(e -> new InfoAlert(SEASON));
        infoBreakTime.setOnAction(e -> new InfoAlert(BREAK_TIME));
        startSimulation.setOnAction(e -> new Simulation(this));
        showInAndOutSets.setOnAction(e -> IOSetsStage.init());
    }

}


