package main;

import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import main.mainPanes.MainScrollPane;
import main.validation.TextFieldWithValidation;

public class StageItems {
    private final int compWidth = 200;

    static void init(Main main, Stage window) {
        new StageItems(main, window);
    }

    private StageItems(Main main, Stage window)
    {
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

    private void setWindowProperties(Main main, Stage window){
        window.setTitle("Kontroler pieca na logice rozmytej");
        window.setWidth(800);
        window.setHeight(700);
        window.setScene(new Scene(new MainScrollPane(main)));
        window.show();
    }

    private void setButtons(Main main){
        Image image = new Image(getClass().getResourceAsStream("/img/info.png"));

        Button infoIsolation = new Button();
        infoIsolation.setGraphic(new ImageView(image));
        infoIsolation.setStyle("-fx-background-color: transparent;");
        main.setInfoIsolation(infoIsolation);

        Button infoSeason = new Button();
        infoSeason.setGraphic(new ImageView(image));
        infoSeason.setStyle("-fx-background-color: transparent;");
        main.setInfoSeason(infoSeason);

        Button infoBreakTime = new Button();
        infoBreakTime.setGraphic(new ImageView(image));
        infoBreakTime.setStyle("-fx-background-color: transparent;");
        main.setInfoBreakTime(infoBreakTime);

        Button startSimulation = new Button();
        Label label = new Label("Start symulacji");
        label.setStyle("-fx-effect: dropshadow( one-pass-box , black , 6 , 0.2 , 2 , 2 ) ; -fx-text-fill: #e8e8e8;");
        startSimulation.setGraphic(label);
        startSimulation.setStyle("-fx-background-color: linear-gradient(#dc81ab, #ab4c49)");
        startSimulation.setMinSize(compWidth,45);
        startSimulation.setMaxSize(compWidth,45);
        main.setStartSimulation(startSimulation);

        Button showInAndOutSets = new Button();
        Label label2 = new Label("Pokaż zbiory rozmyte");
        showInAndOutSets.setGraphic(label2);
        showInAndOutSets.setStyle("-fx-background-color: linear-gradient(#9ddca5, #4b9cff)");
        showInAndOutSets.setMinSize(compWidth, 45);
        showInAndOutSets.setMaxSize(compWidth, 45);
        main.setShowInAndOutSets(showInAndOutSets);
    }

    private void setTextFields(Main main){
        main.setAreaField(new TextFieldWithValidation("150", false));
        main.setHeightField(new TextFieldWithValidation("2.5",false));
        main.setPowerField(new TextFieldWithValidation("5",false));
        main.setStartTempField(new TextFieldWithValidation("12",true));
        main.setOptTempField(new TextFieldWithValidation("25",true));
    }

    private void setLabels(Main main){
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

    private void setChoiceBoxes(Main main){
        ChoiceBox<String> seasonChoiceBox = new ChoiceBox<>();
        seasonChoiceBox.getItems().addAll("Wiosna", "Lato", "Jesień", "Zima");
        seasonChoiceBox.setValue("Zima");
        main.setSeasonChoiceBox(seasonChoiceBox);

        ChoiceBox<String> isolationChoiceBox = new ChoiceBox<>();
        isolationChoiceBox.getItems().addAll("Brak", "Styropian 15cm", "Wełna mineralna 15cm");
        isolationChoiceBox.setValue("Styropian 15cm");
        main.setIsolationChoiceBox(isolationChoiceBox);

        ChoiceBox<String> breakTimeChoiceBox = new ChoiceBox<>();
        breakTimeChoiceBox.getItems().addAll("Brak", "1/10 sekundy", "1/5 sekundy", "1/2 sekundy", "1 sekunda");
        breakTimeChoiceBox.setValue("1/5 sekundy");
        main.setBreakTimeChoiceBox(breakTimeChoiceBox);
    }

    private void setStyleForAllLabels(Label... labels){
        for(Label label : labels){
            label.setStyle("-fx-text-fill: white");
        }
    }

    private void setSizeForAllControls(Control... controls){
        for(Control control : controls){
            control.setMinSize(compWidth, 32);
            control.setMaxSize(compWidth, 32);
        }
    }
}
