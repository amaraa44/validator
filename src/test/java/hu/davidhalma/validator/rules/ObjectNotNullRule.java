package hu.davidhalma.validator.rules;

import hu.davidhalma.validator.Rule;

public class ObjectNotNullRule implements Rule {

    @Override
    public boolean isValid(Object o) {
        return null != o;
    }
}
