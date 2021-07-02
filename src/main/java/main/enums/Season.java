package main.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Getter
public enum Season {
    SPRING("Wiosna"),
    SUMMER("Lato"),
    AUTUMN("Jesień"),
    WINTER("Zima");
    String value;

    public static Season getByValue(String value) {
        return Arrays.stream(values()).filter(e -> e.getValue().equals(value)).findFirst().orElse(null);
    }

    public static List<String> getValues(){
        return Arrays.stream(values()).map(Season::getValue).collect(Collectors.toList());
    }
}
