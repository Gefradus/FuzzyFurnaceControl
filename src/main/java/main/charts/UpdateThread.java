package main.charts;

import javafx.application.Platform;
import javafx.scene.chart.XYChart;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class UpdateThread extends Thread
{
    public UpdateThread(int sleepMillisecond, double[] getTempOrPower, XYChart.Series<String, Number> series){
        try
        {
            Calendar cal = Calendar.getInstance();
            cal.clear();
            for(int i = 0; i <= 1439; i++){
                cal.add(Calendar.MINUTE, 1);
                Thread.sleep(sleepMillisecond);
                Date time = cal.getTime();
                int finalI = i;
                Platform.runLater(() -> series.getData().add(
                        new XYChart.Data<>(new SimpleDateFormat("HH:mm").format(time), getTempOrPower[finalI]))
                );
            }
        }
        catch (InterruptedException ignored) {}
        setDaemon(true);
    }
}
