package main;
import fuzzyLogic.FuzzyLogic;

public class IsolationChoice
{
    public static FuzzyLogic make(FuzzyLogic fuzzyLogic, double area, double height, String isolation)
    {
        fuzzyLogic.setC(1005);   //ciepło właściwe powietrza
        fuzzyLogic.setM(area * height * 1.2); // masa powietrza , 1.2 - gestosc powietrza

        if (isolation.equals("Brak")){
            fuzzyLogic.setK(1.7) ;    //przewodnosc cieplna dla cegly
            fuzzyLogic.setD(0.18);   // grubosc sciany
        }
        if (isolation.equals("Styropian 15cm")){

            fuzzyLogic.setK((0.545 * 1.7) + (0.455 * 0.03)); //45.5% grubości styropian o przewodnosci cieplnej 0.03
            fuzzyLogic.setD(0.33); // 0.18 + 0.15
        }
        if (isolation.equals("Wełna mineralna 15cm")){

            fuzzyLogic.setK((0.545 * 1.7) + (0.455 * 0.04)); //45.5% grubości wełna o przewodnosci cieplnej 0.04
            fuzzyLogic.setD(0.33); // 0.18 + 0.15
        }

        return fuzzyLogic;
    }
}
