package main.charts;

import fuzzyLogic.FuzzyLogic;
import javafx.stage.Screen;


public class ChartsCreator
{
    private static boolean chartsVisible;
    private static RealTimeChart insideTempChart;
    private static RealTimeChart powerTempChart;
    private static RealTimeChart outsideTempChart;

    public static void create(FuzzyLogic fuzzyLogic)
    {
        int breakTime = fuzzyLogic.getBreakTime();
        double maxX = Screen.getPrimary().getBounds().getMaxX();

        if(!chartsVisible){
            chartsVisible = true;

            insideTempChart = new RealTimeChart(ChartType.inside_temp, download(fuzzyLogic.getInsideTemp()), breakTime);
            insideTempChart.setXY(maxX - insideTempChart.getWidth(),0);
            insideTempChart.setOnCloseRequest(e -> closeAllCharts());

            powerTempChart = new RealTimeChart(ChartType.power, download(fuzzyLogic.getPower()), breakTime);
            powerTempChart.setXY(maxX / 2 - powerTempChart.getWidth() / 2, Screen.getPrimary().getBounds().getMaxY() - powerTempChart.getHeight());
            powerTempChart.setOnCloseRequest(e -> closeAllCharts());

            outsideTempChart = new RealTimeChart(ChartType.outside_temp, download(fuzzyLogic.getOutsideTemp()), breakTime);
            outsideTempChart.setXY(0,0);
            outsideTempChart.setOnCloseRequest(e -> closeAllCharts());
        }
    }

    private static void closeAllCharts(){
        insideTempChart.close();
        powerTempChart.close();
        outsideTempChart.close();
        chartsVisible = false;
    }

    private static double[] download(double[] array) {
        double[] newArray = new double[1440];
        int number = 0;
        for(int i = 0; i < 86400; i += 60){      //co minute pobieramy
            newArray[number] = array[i];
            number++;
        }
        return newArray;
    }


}
