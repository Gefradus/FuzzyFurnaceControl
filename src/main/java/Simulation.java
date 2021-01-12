import alerts.ErrorAlert;

public class Simulation
{
    public Simulation(Main main){
        try
        {
            double area = Double.parseDouble(main.getAreaField().getText());
            double height = Double.parseDouble(main.getHeightField().getText());
            double power = Double.parseDouble(main.getPowerField().getText());

            if (power >= 0 & height > 0 & area > 0 ) {
                FuzzyLogic fuzzyLogic = new FuzzyLogic();
                fuzzyLogic.setOutsideTemp(TemperatureFromFileHandler.chooseSeasonTemperatures(main.getSeasonChoiceBox().getValue()));
                fuzzyLogic.setStartT(Double.parseDouble(main.getStartTempField().getText()));
                fuzzyLogic.setArea(area);
                fuzzyLogic.setHeight(height);
                fuzzyLogic.setOptT(Double.parseDouble(main.getOptTempField().getText()));
                fuzzyLogic.setPowerMax(power);
                fuzzyLogic.setBreakTime(main.getBreakTimeChoiceBox().getValue());

                new IsolationChoice(fuzzyLogic, area, height, main.getIsolationChoiceBox().getValue()).make().start();
            }
            else {
                new ErrorAlert("Powierzchnia, wysokość, oraz moca pieca muszą być dodatnimi wartościami",
                        "Ustaw podane pola na dodatnie wartości i spróbuj ponownie");
            }
        }
        catch (Exception ignored)
        {
            new ErrorAlert("Wszystkie pola muszą zawierać wartości liczbowe",
                    "Ustaw pola na wartości liczbowe i spróbuj ponownie");
        }
    }
}
