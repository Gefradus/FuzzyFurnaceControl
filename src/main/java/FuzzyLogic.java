import lombok.Setter;
import java.util.LinkedList;

public class FuzzyLogic
{
    @Setter
    private double[] outsideTemp;
    @Setter
    private double startT, area, height, optT, powerMax;
    @Setter
    private int breakTime;
    @Setter
    private double d, c, m, k;
    private double[] insideTemp, power, Δt, ΔT;
    private double xIn, xOut;
    private final double tempInMissVLowTop = 2;
    private final double tempInMissLowBottom = 1;
    private final double tempInMissLowTop = 5;
    private final double tempInMissMediumBottom = 3.5;
    private final double tempInMissMediumTop = 7;
    private final double tempInMissHighBottom = 6;
    private final double tempInMissHighTop = 15;
    private final double tempInMissVHighBottom = 10;
    private double heating_vVerylow, heating_vlow, heating_low, heating_littleLow, heating_medium, heating_littleHigh, heating_High, heating_vHigh, heating_vVeryHigh;
    private double tempOutMissingLowBottom, tempOutMissingLowTop, tempOutMissingMediumTop, tempOutMissingHighBottom, tempOutMissingHighTop;

    public void start() {
        power = new double[86400];
        insideTemp = new double[86401];
        Δt = new double[86400];
        ΔT = new double[86400];

        insideTemp[0] = startT;
        power[0] = 0;
        setHeatingPowerPercentage();

        double[] tempIncrease = new double[86400];
        for (int i=0; i<=86399; i++)
        {
            tempOutMissingLowBottom = 0;
            tempOutMissingLowTop = 37.5;
            tempOutMissingMediumTop = 75;
            tempOutMissingHighBottom = 37.5;
            tempOutMissingHighTop = 75;

            ΔT[i] = outsideTemp[i] - insideTemp[i];           // ΔT jest to różnica temperatury zewnetrznej i wewnetrznej
            Δt[i] = (k * Math.sqrt(area) * height * ΔT[i]) / (m * c * d);  //10 sekund czyli 1 iteracja
            // Δt jest to zmiana temperatury wewnętrznej pod wpływem temperatury zewnętrznej

            createRules(i);

            tempIncrease[i] = (power[i] * 1000) / (m * c);     //1kW to 1000W, 10 sekund trwa iteracja
            insideTemp[i + 1] = insideTemp[i] + Δt[i] + tempIncrease[i];
            // https://www.physicsclassroom.com/Class/thermalP/u18l1f.cfm?fbclid=IwAR2_gFvWmWkDTSpODqvbuMBw9niOQetVdwZ5idIsIy3hLV6uMY65G7YGCyw
            // https://pl.wikipedia.org/wiki/Przewodzenie_ciep%C5%82a
            // równanie Fouriera( q = (k * A * deltaT)/d )

            //https://pl.khanacademy.org/science/chemistry/thermodynamics-chemistry/internal-energy-sal/a/heat
            // Q = m * c * Δt
        }

        new RealTimeChart(getTempIn(), "Temperatury wewnętrzne w czasie", breakTime);
        new RealTimeChart(getPower(),"Moc pieca w czasie", breakTime);
        new RealTimeChart(getTempOut(outsideTemp), "Temperatury zewnętrzne w czasie", breakTime);
    }

    private void createRules(int i){
        xIn = optT - insideTemp[i];
        xOut = optT - outsideTemp[i];

        LinkedList<Rule> ruleList = new LinkedList<>();

        // Wyznaczanie reguł z zapłonem

        //wew bardzo mala
        if(affiliationToInsideMissingVeryLow()>0 && affiliationToOutMissingLow() >0){
            ruleList.add(new Rule(1, affiliationToInsideMissingVeryLow(), affiliationToOutMissingLow()));
        }
        if(affiliationToInsideMissingVeryLow() > 0 && affiliationToOutMissingMedium() > 0){
            ruleList.add(new Rule(2, affiliationToInsideMissingVeryLow(), affiliationToOutMissingMedium()));
        }

        if(affiliationToInsideMissingVeryLow() > 0 && affiliationToOutMissingHigh() > 0){
            ruleList.add(new Rule(3, affiliationToInsideMissingVeryLow(), affiliationToOutMissingHigh()));
            //  ruleList.add(new Regula());
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
    
    private void centreOfGravityMethod(int i, LinkedList<Rule> ruleListWithIgnite){
        LinkedList<Rule> ruleList = RulesConflictSolver.solveConflict(ruleListWithIgnite);
        
        double numerator = 0;
        double denominator = 0;
        power[i] = 0;

        for (Rule rule : ruleList) {
            numerator += ruleWeight(rule);
        }

        for (Rule rule : ruleList) {
            denominator += rule.getIgnition();
        }

        if(denominator!=0) {
            power[i] = numerator / denominator;
        }
    }
    

    private double ruleWeight(Rule rule){
        int number = rule.getNumber();
        double ignition = rule.getIgnition();
        double heating;
        if(number == 1){
            heating = heating_vVerylow;
        }
        else if(number == 2){
            heating = heating_vlow;
        }
        else if(number == 3){
            heating = heating_low;
        }
        else if(number == 4){
            heating = heating_vlow;
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



    private void setHeatingPowerPercentage(){
        heating_vVerylow = powerMax * 0.3;
        heating_vlow = powerMax * 0.4;
        heating_low = powerMax * 0.5;
        heating_littleLow = powerMax * 0.6;
        heating_medium = powerMax * 0.7;
        heating_littleHigh = powerMax * 0.8;
        heating_High = powerMax * 0.9;
        heating_vHigh = powerMax * 0.95;
        heating_vVeryHigh = powerMax;
    }


    private double[] getTempIn(){
        double[] newInsideTemp = new double[1440];
        int number = 0;
        for(int i=0; i<86400; i=i+60){      //co minute pobieramy temperature wewnetrzna
            newInsideTemp[number] = insideTemp[i];
            number++;
        }
        return newInsideTemp;
    }

    private double[] getPower(){
        double[] newPower = new double[1440];
        int number = 0;
        for(int i=0; i<86400; i=i+60){     //co minute pobieramy moc
            newPower[number] = power[i];
            number++;
        }
        return newPower;
    }

    private double[] getTempOut(double[] outsideT) {
        double[] newOutsideTemp = new double[1440];
        int number = 0;
        for(int i=0; i<86400; i=i+60){      //co minute pobieramy temperature zewnetrzna
            newOutsideTemp[number] = outsideT[i];
            number++;
        }
        return newOutsideTemp;
    }
    

}




