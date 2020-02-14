package org.example.rule;

public class GreaterThanZeroRule implements Rule {
    @Override
    public boolean isValid(Object o) {
        if (o instanceof Integer){
            Integer n = (Integer) o;
            return 0 < n;
        }
        return false;
    }
}
