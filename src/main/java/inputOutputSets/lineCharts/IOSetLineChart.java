package inputOutputSets.lineCharts;

import javafx.scene.chart.Axis;
import javafx.scene.chart.LineChart;

public class IOSetLineChart extends LineChart<Number,Number>
{
    public IOSetLineChart(Axis<Number> xAxis, Axis<Number> yAxis) {
        super(xAxis, yAxis);
        setPrefSize(750, 320);
        yAxis.setLabel("μ");
    }
}
