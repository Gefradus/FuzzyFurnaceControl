import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class RealTimeChart extends Stage
{
    private XYChart.Series<String, Number> series;

    public RealTimeChart(double[] getTempOrPower, String chartTitle, int sleepMillisecond)
    {
        setScene(new Scene(createInstanceOfChart(chartTitle), 800, 600));
        setTitle(chartTitle);
        show();

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm");
        Thread updateThread = new Thread(() -> {
            try
            {
                Calendar cal = Calendar.getInstance();
                cal.clear();
                for(int i = 0; i <= 1439; i++){
                    cal.add(Calendar.MINUTE, 1);
                    Thread.sleep(sleepMillisecond);
                    Date time = cal.getTime();
                    int finalI = i;
                    Platform.runLater(() -> series.getData().add(new XYChart.Data<>(simpleDateFormat.format(time), getTempOrPower[finalI])));
                }
            }
            catch (Exception ignored) {}
        });

        updateThread.setDaemon(true);
        updateThread.start();
    }

    private LineChart<String, Number> createInstanceOfChart(String chartTitle){
        final CategoryAxis xAxis = new CategoryAxis();
        final NumberAxis yAxis = new NumberAxis();

        xAxis.setAnimated(false);
        xAxis.setLabel("Czas");
        yAxis.setAnimated(false);

        series = new XYChart.Series<>();

        if (chartTitle.equals("Moc pieca w czasie")){
            yAxis.setLabel ("P [kW]");
            series.setName("Moc pieca");
        }
        else if (chartTitle.equals("Temperatury zewnętrzne w czasie")){
            yAxis.setLabel ("T [°C]");
            series.setName("Temperatury zewnętrzne");
        }
        else {
            yAxis.setLabel("T [°C]");
            series.setName("Temperatury wewnętrzne");
        }

        LineChart<String, Number> chart = new LineChart<>(xAxis, yAxis);
        chart.setAnimated(false);
        chart.getData().add(series);
        return chart;
    }


}
