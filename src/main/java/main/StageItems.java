package main;

import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import main.enums.BreakTimeType;
import main.enums.IsolationChoiceType;
import main.enums.Season;
import main.mainPanes.MainScrollPane;
import main.validation.TextFieldWithValidation;

import static javafx.collections.FXCollections.observableArrayList;
import static main.enums.BreakTimeType.ONE_FIFTH_SECOND;

public class StageItems {
    private final int compWidth = 200;

    static void init(Main main, Stage window) {
        new StageItems(main, window);
    }

    private StageItems(Main main, Stage window) {
        setLabels(main);
        setTextFields(main);
        setChoiceBoxes(main);
        setButtons(main);

        setSizeForAllControls(main.getBreakTimeChoiceBox(),
                main.getIsolationChoiceBox(),
                main.getSeasonChoiceBox(),
                main.getAreaField(),
                main.getHeightField(),
                main.getPowerField(),
                main.getStartTempField(),
                main.getOptTempField());

        setStyleForAllLabels(main.getHeader(),
                main.getAreaLabel(),
                main.getHeightLabel(),
                main.getPowerLabel(),
                main.getStartTempLabel(),
                main.getOptTempLabel(),
                main.getIsolationLabel(),
                main.getBreakTimeLabel(),
                main.getSeasonLabel()
        );

        setWindowProperties(main, window);
    }

    private void setWindowProperties(Main main, Stage window) {
        window.setTitle("Kontroler pieca na logice rozmytej");
        window.setWidth(800);
        window.setHeight(700);
        window.setScene(new Scene(new MainScrollPane(main)));
        window.show();
    }

    private void setButtons(Main main) {
        main.setInfoIsolation(new InfoButton());
        main.setInfoSeason(new InfoButton());
        main.setInfoBreakTime(new InfoButton());

        Button startSimulation = new Button();
        Label label = new Label("Start symulacji");
        label.setStyle("-fx-effect: dropshadow( one-pass-box , black , 6 , 0.2 , 2 , 2 ) ; -fx-text-fill: #e8e8e8;");
        startSimulation.setGraphic(label);
        startSimulation.setStyle("-fx-background-color: linear-gradient(#dc81ab, #ab4c49)");
        startSimulation.setMinSize(compWidth, 45);
        startSimulation.setMaxSize(compWidth, 45);
        main.setStartSimulation(startSimulation);

        Button showInAndOutSets = new Button();
        Label label2 = new Label("Pokaż zbiory rozmyte");
        showInAndOutSets.setGraphic(label2);
        showInAndOutSets.setStyle("-fx-background-color: linear-gradient(#9ddca5, #4b9cff)");
        showInAndOutSets.setMinSize(compWidth, 45);
        showInAndOutSets.setMaxSize(compWidth, 45);
        main.setShowInAndOutSets(showInAndOutSets);
    }

    private void setTextFields(Main main) {
        main.setAreaField(new TextFieldWithValidation("150", false));
        main.setHeightField(new TextFieldWithValidation("2.5", false));
        main.setPowerField(new TextFieldWithValidation("5", false));
        main.setStartTempField(new TextFieldWithValidation("12", true));
        main.setOptTempField(new TextFieldWithValidation("25", true));
    }

    private void setLabels(Main main) {
        main.setAreaLabel(new Label("Powierzchnia[m2]:"));
        main.setHeightLabel(new Label("Wysokość[m]:"));
        main.setPowerLabel(new Label("Moc pieca[kW]:"));
        main.setStartTempLabel(new Label("Temperatura początkowa[°C]:"));
        main.setOptTempLabel(new Label("Optymalna temperatura[°C]:"));
        main.setIsolationLabel(new Label("Izolacja pomieszczenia:"));
        main.setSeasonLabel(new Label("Pora roku:"));
        main.setBreakTimeLabel(new Label("Czas przerwy między próbkami:"));

        Label header = new Label("Parametry pomieszczenia:");
        header.setFont(Font.font("Verdana", 20));
        main.setHeader(header);
    }

    private void setChoiceBoxes(Main main) {
        ChoiceBox<String> seasonChoiceBox = new ChoiceBox<>();
        seasonChoiceBox.getItems().addAll(observableArrayList(Season.getValues()));
        seasonChoiceBox.setValue(Season.WINTER.getValue());
        main.setSeasonChoiceBox(seasonChoiceBox);

        ChoiceBox<String> isolationChoiceBox = new ChoiceBox<>();
        isolationChoiceBox.setItems(observableArrayList(IsolationChoiceType.getValues()));
        isolationChoiceBox.setValue(IsolationChoiceType.STYROFOAM.getValue());
        main.setIsolationChoiceBox(isolationChoiceBox);

        ChoiceBox<String> breakTimeChoiceBox = new ChoiceBox<>();
        breakTimeChoiceBox.setItems(observableArrayList(BreakTimeType.getValues()));
        breakTimeChoiceBox.setValue(ONE_FIFTH_SECOND.getValue());
        main.setBreakTimeChoiceBox(breakTimeChoiceBox);
    }

    private void setStyleForAllLabels(Label... labels) {
        for (Label label : labels) {
            label.setStyle("-fx-text-fill: white");
        }
    }

    private void setSizeForAllControls(Control... controls) {
        for (Control control : controls) {
            control.setMinSize(compWidth, 32);
            control.setMaxSize(compWidth, 32);
        }
    }
}
