package inputOutputSets.panes;

import inputOutputSets.lineCharts.IOSetFurnaceIntensityPower;
import inputOutputSets.lineCharts.IOSetOptimalInsideTemp;
import inputOutputSets.lineCharts.IOSetOptimalOutsideTemp;
import panes.BasicFlowPane;
import javafx.scene.chart.NumberAxis;
import javafx.scene.control.Label;

public class IOSetFlowPane extends BasicFlowPane {
    public IOSetFlowPane()
    {
        getChildren().addAll(
            new IOSetOptimalInsideTemp(new NumberAxis(), new NumberAxis()),
            new Label("*Term 'Bardzo dużo' został skrócony w celu wyraźnej demonstracji zbioru." +
                    "\nJego górna granica w rzeczywistości wynosi 70°C (na wykresie 20°C)."),
            new IOSetOptimalOutsideTemp(new NumberAxis(), new NumberAxis()),
            new IOSetFurnaceIntensityPower(new NumberAxis(), new NumberAxis())
        );
    }
}
