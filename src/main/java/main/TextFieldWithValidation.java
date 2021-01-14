package main;

import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.util.StringConverter;
import java.util.function.UnaryOperator;
import java.util.regex.Pattern;


public class TextFieldWithValidation extends TextField
{
    final Pattern basicRegex = Pattern.compile("(([1-9][0-9]*)|0)?(\\.[0-9]*)?");
    final Pattern regexForTemperatures = Pattern.compile("-?(([1-9][0-9]*)|0)?(\\.[0-9]*)?");

    public TextFieldWithValidation(String s, boolean forTemperatures) {
        setTextFormatter(new TextFormatter<>(
                createConverter(),
                Double.parseDouble(s),
                createFilter(forTemperatures ? regexForTemperatures : basicRegex)
        ));
    }

    private UnaryOperator<TextFormatter.Change> createFilter(Pattern regex){
        return filter -> {
            String text = filter.getControlNewText();
            if (regex.matcher(text).matches()) {
                return filter;
            } else {
                return null;
            }
        };
    }

    private StringConverter<Double> createConverter(){
        return new StringConverter<Double>() {
            @Override
            public Double fromString(String s) {
                if (s.isEmpty() || "-".equals(s) || ",".equals(s) || "-.".equals(s)) {
                    return 0.0 ;
                } else {
                    return Double.valueOf(s);
                }
            }

            @Override
            public String toString(Double d) {
                return d.toString();
            }
        };
    }
}
