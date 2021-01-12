public class BreakTime {
    public static int chooseBreakTime(String breakTime){
        switch(breakTime)
        {
            case "1 sekunda" :
                return 1000;
            case "1/2 sekundy":
                return 500;
            case "1/5 sekundy":
                return 200;
            case "1/10 sekundy":
                return 100;
            default:
                return 0;
        }
    }
}
