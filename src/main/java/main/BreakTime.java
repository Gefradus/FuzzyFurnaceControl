package main;

import main.enums.BreakTimeType;

public class BreakTime {
    public static int chooseBreakTime(String type) {
        switch (BreakTimeType.getByValue(type)) {
            case SECOND:
                return 1000;
            case HALF_SECOND:
                return 500;
            case ONE_FIFTH_SECOND:
                return 200;
            case ONE_TENTH_SECOND:
                return 100;
            default:
                return 0;
        }
    }
}
