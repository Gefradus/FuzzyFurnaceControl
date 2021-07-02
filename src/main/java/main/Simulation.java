package main;
import fuzzyLogic.FuzzyLogic;

import static main.IsolationChoice.make;
import static main.validation.SimulationValidationHandler.validate;

public class Simulation
{
    public Simulation(Main main) {
        try {
            double area = Double.parseDouble(main.getAreaField().getText());
            double height = Double.parseDouble(main.getHeightField().getText());
            double power = Double.parseDouble(main.getPowerField().getText());

            if (power >= 0 & height > 0 & area > 0) {
                FuzzyLogic fuzzyLogic = new FuzzyLogic(
                    TemperatureFromFileHandler.chooseSeasonTemperatures(main.getSeasonChoiceBox().getValue()),
                    Double.parseDouble(main.getStartTempField().getText()),
                    area,
                    height,
                    Double.parseDouble(main.getOptTempField().getText()),
                    power,
                    BreakTime.chooseBreakTime(main.getBreakTimeChoiceBox().getValue())
                );

                make(fuzzyLogic, area, height, main.getIsolationChoiceBox().getValue()).start();
            }
            else {
                validate(area, height, power);
            }
        }
        catch (Exception e) {
             validate(e);
        }
    }
}
