package main.charts;

import fuzzyLogic.FuzzyLogic;
import javafx.stage.Screen;

public class ChartsCreator {
    public ChartsCreator(FuzzyLogic fuzzyLogic) {
        int breakTime = fuzzyLogic.getBreakTime();
        RealTimeChart insideTempChart = new RealTimeChart(ChartType.inside_temp, download(fuzzyLogic.getInsideTemp()), breakTime);
        RealTimeChart powerTempChart = new RealTimeChart(ChartType.power, download(fuzzyLogic.getPower()), breakTime);
        RealTimeChart outsideTempChart = new RealTimeChart(ChartType.outside_temp, download(fuzzyLogic.getOutsideTemp()), breakTime);

        double maxX = Screen.getPrimary().getBounds().getMaxX();
        outsideTempChart.setXY(0,0);
        insideTempChart.setXY(maxX - insideTempChart.getWidth(),0);
        powerTempChart.setXY(maxX / 2 - powerTempChart.getWidth() / 2, Screen.getPrimary().getBounds().getMaxY() - powerTempChart.getHeight());
    }

    private double[] download(double[] array) {
        double[] newArray = new double[1440];
        int number = 0;
        for(int i = 0; i < 86400; i += 60){      //co minute pobieramy
            newArray[number] = array[i];
            number++;
        }
        return newArray;
    }


}
