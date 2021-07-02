package alerts;

import javafx.scene.control.Alert;
import main.InfoAlertType;

public class InfoAlert extends Alert
{
    private String header;
    private String content;

    public InfoAlert(InfoAlertType message)
    {
        super(Alert.AlertType.INFORMATION);
        setTitle("Dodatkowe informacje");
        chooseHeaderAndContent(message);
        setHeaderText(header);
        setContentText(content);
        showAndWait();
    }

    private void chooseHeaderAndContent(InfoAlertType message){
        if(message.equals(InfoAlertType.BREAK_TIME))
        {
            header = "Czas rzeczywisty odpowiadający następnej próbce pomiaru na wykresie.\n" +
                    "Czas przerwy na wykresie (między próbkami) wynosi 1 minutę ";
            content = "Brak - Natychmiastowe rysowanie przebiegu z całego dnia\n" +
                      "1/10 sekundy - W ciągu sekundy pojawi się 10 próbek pomiarów (10 minut)\n" +
                      "1/5 sekundy - W ciągu sekundy pojawi się 5 próbek pomiarów (5 minut)\n" +
                      "1/2 sekundy - W ciągu sekundy pojawią się 2 próbki pomiarów (2 minuty)\n" +
                      "1 sekunda - W ciągu sekundy pojawi się próbka pomiarów (1 minuta)";
        }
        else if(message.equals(InfoAlertType.SEASON)){
            header = "Wybór temperaturych zewnętrznych w ciągu dnia na podstawie przykładowych pomiarów.";
            content = "Wiosna - (Kraków, 5 kwietnia 2019r)\n" +
                      "Lato -  (Gdańsk, 15 Lipca 2019r)\n" +
                      "Jesień - (Gdańsk, 10 listopada 2019r)\n" +
                      "Zima - (Bielsko-biała,, 19 stycznia 2019r) ";
        }
        else {
            header = "Ściany pomieszczenia mają grubość 18cm i wykonane są z żelbetonu";
            content = "Izolacja jest dodatkowym materiałem izolacyjnym dodawanym do ścian";
        }
    }

}
