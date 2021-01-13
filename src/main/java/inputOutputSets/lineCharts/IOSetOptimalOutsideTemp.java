package inputOutputSets.lineCharts;

import javafx.scene.chart.Axis;
import javafx.scene.chart.XYChart;

public class IOSetOptimalOutsideTemp extends IOSetLineChart
{
    public IOSetOptimalOutsideTemp(Axis<Number> xAxis, Axis<Number> yAxis) {
        super(xAxis, yAxis);
        xAxis.setLabel("Optymalna temp. - zewnętrzna temp. [°C]");
        setTitle("Zbiór wejściowy 2: Ile brakuje temperaturom zewnętrznym do optymalnej");

        XYChart.Series<Number, Number> series = new XYChart.Series<>();
        series.setName("Mało");
        series.getData().add(new XYChart.Data<>(0, 1));
        series.getData().add(new XYChart.Data<>(37.5, 0));

        XYChart.Series<Number, Number> series2 = new XYChart.Series<>();
        series2.setName("Średnio");
        series2.getData().add(new XYChart.Data<>(0, 0));
        series2.getData().add(new XYChart.Data<>(37.5, 1));
        series2.getData().add(new XYChart.Data<>(75, 0));

        XYChart.Series<Number, Number> series3 = new XYChart.Series<>();
        series3.setName("Dużo");
        series3.getData().add(new XYChart.Data<>(37.5, 0));
        series3.getData().add(new XYChart.Data<>(75, 1));

        getData().addAll(series, series2, series3);
    }

}
