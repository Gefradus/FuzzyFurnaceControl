package main.charts;

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

    public RealTimeChart(ChartType chartType, double[] getTempOrPower, int sleepMillisecond)
    {
        setScene(new Scene(createInstanceOfChart(chartType), 800, 600));
        show();

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
                    Platform.runLater(() -> series.getData().add(
                            new XYChart.Data<>(new SimpleDateFormat("HH:mm").format(time), getTempOrPower[finalI])));
                }
            }
            catch (Exception ignored) {}
        });

        updateThread.setDaemon(true);
        updateThread.start();
    }

    private LineChart<String, Number> createInstanceOfChart(ChartType chartType){
        final CategoryAxis xAxis = new CategoryAxis();
        final NumberAxis yAxis = new NumberAxis();

        xAxis.setAnimated(false);
        yAxis.setAnimated(false);
        xAxis.setLabel("Czas");

        series = new XYChart.Series<>();

        if (chartType == ChartType.power) {
            yAxis.setLabel ("P [kW]");
            series.setName("Moc pieca");
            setTitle("Moc pieca w czasie");
        }
        else if (chartType == ChartType.outside_temp){
            yAxis.setLabel ("T [°C]");
            series.setName("Temperatury zewnętrzne");
            setTitle("Temperatury zewnętrzne w czasie");
        }
        else if (chartType == ChartType.inside_temp) {
            yAxis.setLabel("T [°C]");
            series.setName("Temperatury wewnętrzne");
            setTitle("Temperatury wewnętrzne w czasie");
        }

        LineChart<String, Number> chart = new LineChart<>(xAxis, yAxis);
        chart.setAnimated(false);
        chart.getData().add(series);
        return chart;
    }

    public void setXY(double x, double y){
        setX(x);
        setY(y);
    }
}
