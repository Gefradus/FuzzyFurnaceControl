package fuzzyLogic;

import lombok.Getter;

public class Rule
{
    @Getter
    private final int number;
    @Getter
    private final double ignition;
    private final double internalAffiliation;
    private final double outsideAffiliation;

    public Rule(int number, double internalAffiliation, double outsideAffiliation)
    {
        this.number = number;
        this.internalAffiliation = internalAffiliation;
        this.outsideAffiliation = outsideAffiliation;
        ignition = countIgnition();
    }

    private double countIgnition(){
        return Math.min(internalAffiliation, outsideAffiliation);
    }

}
