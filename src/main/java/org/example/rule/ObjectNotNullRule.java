package org.example.rule;

public class ObjectNotNullRule implements Rule {

    @Override
    public boolean isValid(Object o) {
        return null != o;
    }
}
