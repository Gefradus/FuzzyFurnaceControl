package IOSets.IOSetsLineCharts;

import javafx.scene.chart.Axis;
import javafx.scene.chart.XYChart;

public class IOSetOptimalInsideTemp extends IOSetLineChart{

    public IOSetOptimalInsideTemp(Axis<Number> xAxis, Axis<Number> yAxis) {
        super(xAxis, yAxis);
        setTitle("Zbiór wejściowy 1: Ile brakuje temperaturom wewnętrznym do optymalnej");
        xAxis.setLabel("Optymalna temp. - wewnętrzna temp. [°C]");

        XYChart.Series<Number, Number> series = new XYChart.Series<>();
        series.setName("Bardzo mało");

        series.getData().add(new XYChart.Data<>(0, 1));
        series.getData().add(new XYChart.Data<>(1, 1));
        series.getData().add(new XYChart.Data<>(2, 0));

        XYChart.Series<Number, Number> series2 = new XYChart.Series<>();
        series2.setName("Mało");
        series2.getData().add(new XYChart.Data<>(1, 0));
        series2.getData().add(new XYChart.Data<>(2, 1));
        series2.getData().add(new XYChart.Data<>(3.5, 1));
        series2.getData().add(new XYChart.Data<>(5, 0));

        XYChart.Series<Number, Number> series3 = new XYChart.Series<>();
        series3.setName("Średnio");
        series3.getData().add(new XYChart.Data<>(3.5, 0));
        series3.getData().add(new XYChart.Data<>(5, 1));
        series3.getData().add(new XYChart.Data<>(6, 1));
        series3.getData().add(new XYChart.Data<>(7, 0));

        XYChart.Series<Number, Number> series4 = new XYChart.Series<>();
        series4.setName("Dużo");
        series4.getData().add(new XYChart.Data<>(6, 0));
        series4.getData().add(new XYChart.Data<>(7, 1));
        series4.getData().add(new XYChart.Data<>(10, 1));
        series4.getData().add(new XYChart.Data<>(15, 0));

        XYChart.Series<Number, Number> series5 = new XYChart.Series<>();
        series5.setName("Bardzo dużo*");
        series5.getData().add(new XYChart.Data<>(10, 0));
        series5.getData().add(new XYChart.Data<>(15, 1));
        series5.getData().add(new XYChart.Data<>(20, 1));

        getData().addAll(series, series2, series3, series4, series5);
    }
}
