package IOSets;

import IOSets.IOSetsLineCharts.IOSetFurnaceIntensityPower;
import IOSets.IOSetsLineCharts.IOSetOptimalInsideTemp;
import IOSets.IOSetsLineCharts.IOSetOptimalOutsideTemp;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.chart.NumberAxis;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;

public class IOSetFlowPane extends FlowPane{
    public IOSetFlowPane()
    {
        setPadding(new Insets(5, 5, 5, 5));
        setVgap(5);
        setHgap(5);
        setAlignment(Pos.CENTER);

        getChildren().addAll(
                new IOSetOptimalInsideTemp(new NumberAxis(), new NumberAxis()),
                new Label("*Term 'Bardzo dużo' został skrócony w celu wyraźnej demonstracji zbioru." +
                        "\nJego górna granica w rzeczywistości wynosi 70°C (na wykresie 20°C)."),
                new IOSetOptimalOutsideTemp(new NumberAxis(), new NumberAxis()),
                new IOSetFurnaceIntensityPower(new NumberAxis(), new NumberAxis())
        );
    }

}
