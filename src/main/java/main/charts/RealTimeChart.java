package main.charts;

import javafx.scene.Scene;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;

public class RealTimeChart extends Stage
{
    private final ChartType chartType;
    private XYChart.Series<String, Number> series;

    public RealTimeChart(ChartType chartType, double[] getTempOrPower, int sleepMillisecond)
    {
        this.chartType = chartType;
        setScene(new Scene(createInstanceOfChart(), 800, 600));
        show();

        new UpdateThread(sleepMillisecond, getTempOrPower, series).start();
    }

    private LineChart<String, Number> createInstanceOfChart(){
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


}
