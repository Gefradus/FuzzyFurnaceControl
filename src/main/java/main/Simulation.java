package main;
import fuzzyLogic.FuzzyLogic;
import main.validation.SimulationValidationHandler;

public class Simulation
{
    public Simulation(Main main) {
        try {
            double area = Double.parseDouble(main.getAreaField().getText());
            double height = Double.parseDouble(main.getHeightField().getText());
            double power = Double.parseDouble(main.getPowerField().getText());

            if (power >= 0 & height > 0 & area > 0) {
                FuzzyLogic fuzzyLogic = new FuzzyLogic();
                fuzzyLogic.setOutsideTemp(TemperatureFromFileHandler.chooseSeasonTemperatures(main.getSeasonChoiceBox().getValue()));
                fuzzyLogic.setStartT(Double.parseDouble(main.getStartTempField().getText()));
                fuzzyLogic.setArea(area);
                fuzzyLogic.setHeight(height);
                fuzzyLogic.setOptT(Double.parseDouble(main.getOptTempField().getText()));
                fuzzyLogic.setPowerMax(power);
                fuzzyLogic.setBreakTime(BreakTime.chooseBreakTime(main.getBreakTimeChoiceBox().getValue()));
                IsolationChoice.make(fuzzyLogic, area, height, main.getIsolationChoiceBox().getValue()).start();
            }
            else {
                SimulationValidationHandler.validate(area, height, power);
            }
        }
        catch (Exception e) {
             SimulationValidationHandler.validate(e);
        }
    }
}
