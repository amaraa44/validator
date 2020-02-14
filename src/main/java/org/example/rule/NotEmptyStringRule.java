package org.example.rule;

public class NotEmptyStringRule implements Rule {
    @Override
    public boolean isValid(Object o) {
        if (o instanceof String){
            return 0 < ((String) o).length();
        }
        return false;
    }
}
