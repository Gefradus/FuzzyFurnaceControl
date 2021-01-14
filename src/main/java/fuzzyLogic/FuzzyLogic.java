package fuzzyLogic;

import lombok.Getter;
import lombok.Setter;
import main.charts.ChartsCreator;
import java.util.LinkedList;

public class FuzzyLogic
{
    @Setter @Getter
    private double[] outsideTemp;
    @Getter
    private double[] insideTemp, power;
    @Setter
    private double startT, area, height, optT, powerMax;
    @Setter @Getter
    private int breakTime;
    @Setter
    private double d, c, m, k;
    private double[] dt, dT;
    private double xIn, xOut;
    private final double tempInMissVLowTop = 2;
    private final double tempInMissLowBottom = 1;
    private final double tempInMissLowTop = 5;
    private final double tempInMissMediumBottom = 3.5;
    private final double tempInMissMediumTop = 7;
    private final double tempInMissHighBottom = 6;
    private final double tempInMissHighTop = 15;
    private final double tempInMissVHighBottom = 10;
    private double heating_vVeryLow, heating_vLow, heating_low, heating_littleLow, heating_medium, heating_littleHigh, heating_High, heating_vHigh, heating_vVeryHigh;
    private final double tempOutMissingLowBottom = 0;
    private final double tempOutMissingLowTop = 37.5;
    private final double tempOutMissingMediumTop = 75;
    private final double tempOutMissingHighBottom = 37.5;

    private void defineVars(){
        defineHeatingPowerPercentage();
        power = new double[86400];
        insideTemp = new double[86401];
        dt = new double[86400];
        dT = new double[86400];
        insideTemp[0] = startT;
        power[0] = 0;
    }

    private void defineHeatingPowerPercentage(){
        heating_vVeryLow = powerMax * 0.3;
        heating_vLow = powerMax * 0.4;
        heating_low = powerMax * 0.5;
        heating_littleLow = powerMax * 0.6;
        heating_medium = powerMax * 0.7;
        heating_littleHigh = powerMax * 0.8;
        heating_High = powerMax * 0.9;
        heating_vHigh = powerMax * 0.95;
        heating_vVeryHigh = powerMax;
    }

    public void start() {
        defineVars();

        double[] tempIncrease = new double[86400];
        for (int i=0; i<=86399; i++)
        {
            dT[i] = outsideTemp[i] - insideTemp[i];           // ΔT jest to różnica temperatury zewnetrznej i wewnetrznej
            dt[i] = (k * Math.sqrt(area) * height * dT[i]) / (m * c * d);  //10 sekund czyli 1 iteracja
            // Δt jest to zmiana temperatury wewnętrznej pod wpływem temperatury zewnętrznej

            createRules(i);

            tempIncrease[i] = (power[i] * 1000) / (m * c);     //1kW to 1000W, 10 sekund trwa iteracja
            insideTemp[i + 1] = insideTemp[i] + dt[i] + tempIncrease[i];
            // https://www.physicsclassroom.com/Class/thermalP/u18l1f.cfm?fbclid=IwAR2_gFvWmWkDTSpODqvbuMBw9niOQetVdwZ5idIsIy3hLV6uMY65G7YGCyw
            // https://pl.wikipedia.org/wiki/Przewodzenie_ciep%C5%82a
            // równanie Fouriera( q = (k * A * deltaT)/d )

            //https://pl.khanacademy.org/science/chemistry/thermodynamics-chemistry/internal-energy-sal/a/heat
            // Q = m * c * Δt
        }

        new ChartsCreator(this);
    }


    private void createRules(int i){
        xIn = optT - insideTemp[i];
        xOut = optT - outsideTemp[i];

        LinkedList<Rule> ruleList = new LinkedList<>();

        //wew bardzo mala
        if(affiliationToInsideMissingVeryLow()>0 && affiliationToOutMissingLow() >0){
            ruleList.add(new Rule(1, affiliationToInsideMissingVeryLow(), affiliationToOutMissingLow()));
        }
        if(affiliationToInsideMissingVeryLow() > 0 && affiliationToOutMissingMedium() > 0){
            ruleList.add(new Rule(2, affiliationToInsideMissingVeryLow(), affiliationToOutMissingMedium()));
        }

        if(affiliationToInsideMissingVeryLow() > 0 && affiliationToOutMissingHigh() > 0){
            ruleList.add(new Rule(3, affiliationToInsideMissingVeryLow(), affiliationToOutMissingHigh()));
        }
        //wew mala
        if(affiliationToInsideMissingLow() > 0 && affiliationToOutMissingLow() > 0){
            ruleList.add(new Rule(4, affiliationToInsideMissingLow(), affiliationToOutMissingLow()));
        }
        if(affiliationToInsideMissingLow() > 0 && affiliationToOutMissingMedium() > 0){
            ruleList.add(new Rule(5, affiliationToInsideMissingLow(), affiliationToOutMissingMedium()));
        }
        if(affiliationToInsideMissingLow() > 0 && affiliationToOutMissingHigh() > 0){
            ruleList.add(new Rule(6, affiliationToInsideMissingLow(), affiliationToOutMissingHigh()));
        }
        //srednia
        if(affiliationToInsideMissingMedium() > 0 && affiliationToOutMissingLow() > 0){
            ruleList.add(new Rule(7, affiliationToInsideMissingMedium(), affiliationToOutMissingLow()));
        }
        if(affiliationToInsideMissingMedium() > 0 && affiliationToOutMissingMedium() > 0){
            ruleList.add(new Rule(8, affiliationToInsideMissingMedium(), affiliationToOutMissingMedium()));
        }
        if(affiliationToInsideMissingMedium() > 0 && affiliationToOutMissingHigh() > 0){
            ruleList.add(new Rule(9, affiliationToInsideMissingMedium(), affiliationToOutMissingHigh()));
        }
        //duza
        if(affiliationToInsideMissingHigh() > 0 && affiliationToOutMissingLow() > 0){
            ruleList.add(new Rule(10, affiliationToInsideMissingHigh(), affiliationToOutMissingLow()));
        }
        if(affiliationToInsideMissingHigh() > 0 && affiliationToOutMissingMedium() > 0){
            ruleList.add(new Rule(11, affiliationToInsideMissingHigh(), affiliationToOutMissingMedium()));
        }
        if(affiliationToInsideMissingHigh() > 0 && affiliationToOutMissingHigh() > 0){
            ruleList.add(new Rule(12, affiliationToInsideMissingHigh(), affiliationToOutMissingHigh()));
        }
        //bardzo duza
        if(affiliationToInsideMissingVeryHigh() > 0 && affiliationToOutMissingLow() > 0){
            ruleList.add(new Rule(13, affiliationToInsideMissingVeryHigh(), affiliationToOutMissingLow()));
        }
        if(affiliationToInsideMissingVeryHigh() > 0 && affiliationToOutMissingMedium() > 0){
            ruleList.add(new Rule(14, affiliationToInsideMissingVeryHigh(), affiliationToOutMissingMedium()));
        }
        if(affiliationToInsideMissingVeryHigh() > 0 && affiliationToOutMissingHigh() > 0){
            ruleList.add(new Rule(15, affiliationToInsideMissingVeryHigh(), affiliationToOutMissingHigh()));
        }
        
        centreOfGravityMethod(i, ruleList);
    }
    
    private void centreOfGravityMethod(int i, LinkedList<Rule> ruleListWithIgnite)
    {
        LinkedList<Rule> ruleList = RulesConflictSolver.solveConflict(ruleListWithIgnite);
        double denominator = ruleList.stream().mapToDouble(Rule::getIgnition).sum();
        power[i] = denominator != 0 ? ruleList.stream().mapToDouble(this::ruleWeight).sum() / denominator : 0;
    }
    

    private double ruleWeight(Rule rule){
        int number = rule.getNumber();
        double ignition = rule.getIgnition();
        double heating;
        if(number == 1){
            heating = heating_vVeryLow;
        }
        else if(number == 2){
            heating = heating_vLow;
        }
        else if(number == 3){
            heating = heating_low;
        }
        else if(number == 4){
            heating = heating_vLow;
        }
        else if(number == 5){
            heating = heating_low;
        }
        else if(number == 6){
            heating = heating_littleLow;
        }
        else if(number == 7){
            heating = heating_medium;
        }
        else if(number == 8){
            heating = heating_medium;
        }
        else if(number == 9){
            heating = heating_littleHigh;
        }
        else if(number == 10){
            heating = heating_littleHigh;
        }
        else if(number == 11){
            heating = heating_High;
        }
        else if(number == 12){
            heating = heating_vHigh;
        }
        else if(number == 13){
            heating = heating_High;
        }
        else if(number == 14){
            heating = heating_vHigh;
        }
        else if(number == 15){
            heating = heating_vVeryHigh;
        }
        else {
            return 0;
        }

        return heating * ignition;
    }



// ================  A  G  R  E  G  A  C  J  A   ===========
    private double affiliationToOutMissingLow(){
        if(outsideBetweenLowAndMedium()) {
            return slopeFalling(tempOutMissingLowTop, xOut, tempOutMissingLowBottom);
        }
        else return 0;
    }
    private double affiliationToOutMissingMedium(){
        if(outsideBetweenMediumAndHigh()) {
            return slopeFalling(tempOutMissingMediumTop, xOut, tempOutMissingHighBottom);
        }
        else if (outsideBetweenLowAndMedium()) {
            return slopeRising(tempOutMissingLowBottom, xOut, tempOutMissingLowTop);
        }
        else return 0;
    }
    private double affiliationToOutMissingHigh(){
        if(outsideBetweenMediumAndHigh()){
            double tempOutMissingHighTop = 75;
            return slopeRising(tempOutMissingHighBottom, xOut, tempOutMissingHighTop);
        }
        else return 0;
    }

    private double affiliationToInsideMissingVeryLow(){
        if(insideMissingVeryLow()){
            return 1;
        }
        else if(insideMissingBetweenVeryLowAndLow()){
            return slopeFalling(tempInMissVLowTop, xIn, tempInMissLowBottom);
        }
        else return 0;
    }

    private double affiliationToInsideMissingLow(){
        if(insideMissingBetweenVeryLowAndLow()){
            return slopeRising(tempInMissLowBottom, xIn, tempInMissVLowTop);
        }
        else if (insideMissingLow()){
            return 1;
        }
        else if(insideMissingBetweenLowAndMedium()){
            return slopeFalling(tempInMissLowTop, xIn, tempInMissMediumBottom);
        }
        else return 0;
    }

    private double affiliationToInsideMissingMedium(){
        if(insideMissingBetweenLowAndMedium()){
            return slopeRising(tempInMissMediumBottom, xIn, tempInMissLowTop);
        }
        else if(insideMissingMedium()){
            return 1;
        }
        else if (insideMissingBetweenMediumAndHigh()){
            return slopeFalling(tempInMissMediumTop, xIn, tempInMissHighBottom);
        }
        else return 0;
    }
    private double affiliationToInsideMissingHigh(){
        if(insideMissingBetweenMediumAndHigh()){
            return slopeRising(tempInMissHighBottom, xIn, tempInMissMediumTop);
        }
        else if (insideMissingHigh()){
            return 1;
        }
        else if(insideMissingBetweenHighAndVeryHigh()){
            return slopeFalling(tempInMissHighTop, xIn, tempInMissVHighBottom);
        }
        else return 0;
    }
    private double affiliationToInsideMissingVeryHigh(){
        if(insideMissingBetweenHighAndVeryHigh()){
            return slopeRising(tempInMissVHighBottom, xIn, tempInMissHighTop);
        }
        else if (insideMissingVeryHigh()){
            return 1;
        }
        else return 0;
    }

    private double slopeRising(double a, double x, double xi){
        return (x-a)/(xi-a);
    }

    private double slopeFalling(double b, double x, double xii){
        return (b-x)/(b-xii);
    }


    // =========  warunki w regulach dla wewnetrznych temperatur
    private boolean insideMissingVeryLow(){
        double tempInMissVLowBottom = 0;
        return xIn >= tempInMissVLowBottom && xIn < tempInMissLowBottom;
    }
    private boolean insideMissingBetweenVeryLowAndLow(){
        return xIn >= tempInMissLowBottom && xIn < tempInMissVLowTop;
    }
    private boolean insideMissingLow(){
        return xIn >= tempInMissVLowTop && xIn < tempInMissMediumBottom;
    }
    private boolean insideMissingBetweenLowAndMedium(){
        return xIn >= tempInMissMediumBottom && xIn < tempInMissLowTop;
    }
    private boolean insideMissingMedium(){
        return xIn >= tempInMissLowTop && xIn < tempInMissHighBottom;
    }
    private boolean insideMissingBetweenMediumAndHigh(){
        return xIn >= tempInMissHighBottom && xIn < tempInMissMediumTop;
    }
    private boolean insideMissingHigh(){
        return xIn >= tempInMissMediumTop && xIn < tempInMissVHighBottom;
    }
    private boolean insideMissingBetweenHighAndVeryHigh(){
        return xIn >= tempInMissVHighBottom && xIn < tempInMissHighTop;
    }
    private boolean insideMissingVeryHigh(){
        double tempInMissVHighTop = 70;
        return xIn >= tempInMissHighTop && xIn < tempInMissVHighTop;
    }

    // ====--- warunki w regulach dla zewnetrznych temperatur

    private boolean outsideBetweenLowAndMedium(){
        return xOut >= tempOutMissingLowBottom && xOut < tempOutMissingHighBottom;
    }
    private boolean outsideBetweenMediumAndHigh(){
        return xOut >= tempOutMissingHighBottom && xOut <= tempOutMissingMediumTop;
    }


}




