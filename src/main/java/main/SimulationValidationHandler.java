package main;

import alerts.ErrorAlert;

public class SimulationValidationHandler {
    public SimulationValidationHandler(double area, double height, double power) throws Exception
    {
        if(height <= 0 && area > 0 && power >= 0) {
            new ErrorAlert("Wysokość musi być większa od 0",
                    "Ustaw wysokość na wartość większą od 0 i spróbuj ponownie");
        }
        else if(height > 0 && area <= 0 && power >= 0) {
            new ErrorAlert("Powierzchnia musi być większa od 0",
                    "Ustaw powierzchnię na wartość większą od 0 i spróbuj ponownie");
        }
        else if(height <= 0 && area <= 0 && power >= 0) {
            new ErrorAlert("Powierzchnia oraz wysokość muszą być większe od 0",
                    "Ustaw wartości podanych pól na większe od 0 i spróbuj ponownie");
        }
        else {
            throw new Exception();
        }
    }

    public SimulationValidationHandler(Exception e){
        new ErrorAlert("Błąd","Wystąpił nieoczekiwany błąd");
        e.printStackTrace();
    }
}
