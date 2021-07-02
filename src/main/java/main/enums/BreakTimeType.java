package main.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import java.util.List;
import java.util.Arrays;
import java.util.stream.Collectors;

@AllArgsConstructor
@Getter
public enum BreakTimeType {
    NONE("Brak"),
    SECOND("1 sekunda"),
    HALF_SECOND("1/2 sekundy"),
    ONE_FIFTH_SECOND("1/5 sekundy"),
    ONE_TENTH_SECOND("1/10 sekundy");
    String value;

    public static BreakTimeType getByValue(String value) {
        return Arrays.stream(values()).filter(e -> e.getValue().equals(value)).findFirst().orElse(null);
    }

    public static List<String> getValues(){
        return Arrays.stream(values()).map(BreakTimeType::getValue).collect(Collectors.toList());
    }
}
