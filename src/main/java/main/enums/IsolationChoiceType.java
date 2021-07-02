package main.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Getter
public enum IsolationChoiceType {
    NONE("Brak"),
    STYROFOAM("Styropian 15cm"),
    MINERAL_WOOL("Wełna mineralna 15cm");
    String value;

    public static IsolationChoiceType getByValue(String value) {
        return Arrays.stream(values()).filter(e -> e.getValue().equals(value)).findFirst().orElse(null);
    }

    public static List<String> getValues(){
        return Arrays.stream(values()).map(IsolationChoiceType::getValue).collect(Collectors.toList());
    }
}
