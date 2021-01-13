package fuzzyLogic;

import lombok.Getter;

public class Rule
{
    @Getter
    private final int number;
    @Getter
    private final double ignition;

    public Rule(int number, double internalAffiliation, double outsideAffiliation)
    {
        this.number = number;
        ignition = Math.min(internalAffiliation, outsideAffiliation);
    }
}
