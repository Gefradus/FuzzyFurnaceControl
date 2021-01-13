package inputOutputSets.lineCharts;
import javafx.scene.chart.Axis;
import javafx.scene.chart.XYChart;

public class IOSetFurnaceIntensityPower extends IOSetLineChart {

    public IOSetFurnaceIntensityPower(Axis<Number> xAxis, Axis<Number> yAxis) {
        super(xAxis, yAxis);
        xAxis.setLabel("Intensywność mocy pieca");
        setTitle("Zbiór wyjściowy: Intensywność mocy pieca                                                  ");

        XYChart.Series<Number, Number> series1 = new XYChart.Series<>();
        XYChart.Series<Number, Number> series2 = new XYChart.Series<>();
        XYChart.Series<Number, Number> series3 = new XYChart.Series<>();
        XYChart.Series<Number, Number> series4 = new XYChart.Series<>();
        XYChart.Series<Number, Number> series5 = new XYChart.Series<>();
        XYChart.Series<Number, Number> series6 = new XYChart.Series<>();
        XYChart.Series<Number, Number> series7 = new XYChart.Series<>();
        XYChart.Series<Number, Number> series8 = new XYChart.Series<>();
        XYChart.Series<Number, Number> series9 = new XYChart.Series<>();

        series1.getData().add(new XYChart.Data<>(0.3, 0));
        series1.getData().add(new XYChart.Data<>(0.3, 1));

        series2.getData().add(new XYChart.Data<>(0.4, 0));
        series2.getData().add(new XYChart.Data<>(0.4, 1));

        series3.getData().add(new XYChart.Data<>(0.5, 0));
        series3.getData().add(new XYChart.Data<>(0.5, 1));

        series4.getData().add(new XYChart.Data<>(0.6, 0));
        series4.getData().add(new XYChart.Data<>(0.6, 1));

        series5.getData().add(new XYChart.Data<>(0.7, 0));
        series5.getData().add(new XYChart.Data<>(0.7, 1));

        series6.getData().add(new XYChart.Data<>(0.8, 0));
        series6.getData().add(new XYChart.Data<>(0.8, 1));

        series7.getData().add(new XYChart.Data<>(0.9, 0));
        series7.getData().add(new XYChart.Data<>(0.9, 1));

        series8.getData().add(new XYChart.Data<>(0.95, 0));
        series8.getData().add(new XYChart.Data<>(0.95, 1));

        series9.getData().add(new XYChart.Data<>(1, 0));
        series9.getData().add(new XYChart.Data<>(1, 1));

        series1.setName("Bardzo bardzo mała");
        series2.setName("Bardzo mała");
        series3.setName("Mała");
        series4.setName("Trochę mała");
        series5.setName("Średnia");
        series6.setName("Trochę dużo");
        series7.setName("Dużo");
        series8.setName("Bardzo dużo");
        series9.setName("Bardzo bardzo dużo");

        getData().addAll(series1, series2, series3, series4, series5, series6, series7, series8, series9);
    }
}
