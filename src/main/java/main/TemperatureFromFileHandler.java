package main;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import static main.enums.Season.getByValue;

public class TemperatureFromFileHandler {

    public static double[] chooseSeasonTemperatures(String season)
    {
        switch (getByValue(season)) {
            case SPRING:
                return getTemperatureArraysFromFile(getFileFromResource("/temps/wiosna.txt"));
            case SUMMER:
                return getTemperatureArraysFromFile(getFileFromResource("/temps/lato.txt"));
            case AUTUMN:
                return getTemperatureArraysFromFile(getFileFromResource("/temps/jesień.txt"));
            default:
                return getTemperatureArraysFromFile(getFileFromResource("/temps/zima.txt"));
        }
    }

    private static String getFileFromResource(String fileName){
        return TemperatureFromFileHandler.class.getResource(fileName).getFile();
    }

    private static double[] getTemperatureArraysFromFile(String filePath){
        ArrayList<Double> listOfTemperatures = createListOfTemperatures(getTemperaturesFromFile(filePath));

        double[] tempsArray = new double[listOfTemperatures.size()];
        for(int i=0; i < tempsArray.length; i++) {
            tempsArray[i] = listOfTemperatures.get(i);
        }

        return tempsArray;
    }

    private static Scanner getTemperaturesFromFile(String filePath){
        try {
            return new Scanner(new File(filePath));
        }
        catch (FileNotFoundException e) {
            System.err.println("File not found");
            return null;
        }
    }

    private static ArrayList<Double> createListOfTemperatures(Scanner scanner)
    {
        double[] temperaturesFromFile = new double[25];

        for(int i = 0; i < 25; i++) {
            temperaturesFromFile[i] = scanner.nextDouble();
        }

        return determine(temperaturesFromFile);
    }

    private static ArrayList<Double> determine(double[] temps){
        double T1, T2,t1,t2;
        ArrayList<Double> newTemps = new ArrayList<>();
        for(int i = 0; i<(temps.length-1); i++){
            T1=temps[i];
            T2=temps[i+1];
            t2 = 3600;
            //double[0] to x, double[1] to y
            double[] variables = linearSystem(T1, T2,t2);

            //y - temperatura, x - czas
            //T = a*t + b
            for(int j=0; j< t2; j++){
                t1=j;
                double result = variables[0]*t1 + variables[1];
                newTemps.add(result);
            }
        }
        return newTemps;
    }

    private static double[] linearSystem(double T1, double T2, double t2){
        double b1 = 1;
        double b2 = 1;
        double w,wx,wy,x,y;
        w = (double) 0 - b1*t2;
        wx = T1*b2 - b1*T2;
        wy = (double) 0 - T1*t2;
        x = wx/w;
        y = wy/w;
        if(w!= 0){
            return new double[] {x,y};
        }
        else
        {
            return null;
        }
    }
}
