package main;
import fuzzyLogic.FuzzyLogic;

import static main.enums.IsolationChoiceType.getByValue;

public class IsolationChoice
{
    public static FuzzyLogic make(FuzzyLogic fuzzyLogic, double area, double height, String isolation)
    {
        fuzzyLogic.setC(1005);   //ciepło właściwe powietrza
        fuzzyLogic.setM(area * height * 1.2); // masa powietrza , 1.2 - gestosc powietrza

        switch (getByValue(isolation)) {
            case NONE:
                fuzzyLogic.setK(1.7);    //przewodnosc cieplna dla cegly
                fuzzyLogic.setD(0.18);   // grubosc sciany
                break;

            case STYROFOAM:
                fuzzyLogic.setK((0.545 * 1.7) + (0.455 * 0.03)); //45.5% grubości styropian o przewodnosci cieplnej 0.03
                fuzzyLogic.setD(0.33); // 0.18 + 0.15
                break;

            case MINERAL_WOOL:
                fuzzyLogic.setK((0.545 * 1.7) + (0.455 * 0.04)); //45.5% grubości wełna o przewodnosci cieplnej 0.04
                fuzzyLogic.setD(0.33); // 0.18 + 0.15
                break;
        }

        return fuzzyLogic;
    }
}
