package hu.davidhalma.validator.rules;

import hu.davidhalma.validator.Rule;

public class NotEmptyStringRule implements Rule {
    @Override
    public boolean isValid(Object o) {
        if (o instanceof String) {
            return 0 < ((String) o).length();
        }
        return false;
    }
}
