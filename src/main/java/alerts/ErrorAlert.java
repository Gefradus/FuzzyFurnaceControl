package alerts;

import javafx.scene.control.Alert;

public class ErrorAlert extends Alert
{
    public ErrorAlert(String headerText, String contentText)
    {
        super(Alert.AlertType.INFORMATION);
        setTitle("BŁĄD");
        setHeaderText(headerText);
        setContentText(contentText);
        showAndWait();
    }
}
