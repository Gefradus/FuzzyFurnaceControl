public class Rule
{
    private final int number;
    private final double internalAffiliation;
    private final double outsideAffiliation;
    private final double ignition;

    public Rule(int numer, double przynaleznoscDoWewnetrznej, double przynaleznoscDoZewnetrznej)
    {
        number = numer;
        internalAffiliation = przynaleznoscDoWewnetrznej;
        outsideAffiliation = przynaleznoscDoZewnetrznej;
        ignition = wyznaczZaplon();
    }

    private double wyznaczZaplon(){
        return Math.min(internalAffiliation, outsideAffiliation);
    }

    public double getIgnition() {
        return ignition;
    }

    public int getNumber() {
        return number;
    }

}
