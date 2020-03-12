package hu.davidhalma.validator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Validator {
    private List<Object> validatableObjects;
    private List<Rule> rules;
    private RuntimeException exception;

    public List<Object> getValidatableObjects() {
        return validatableObjects;
    }

    public void setValidatableObjects(List<Object> validatableObjects) {
        this.validatableObjects = validatableObjects;
    }

    public void validate() {
        for (Object object :
                validatableObjects) {
            for (Rule rule :
                    rules) {
                throwIfNotValid(object, rule);
            }
        }
    }

    private void throwIfNotValid(Object o, Rule rule) {
        if (!rule.isValid(o)) {
            throw exception;
        }
    }

    public List<Rule> getRules() {
        return rules;
    }

    public void setRules(List<Rule> rules) {
        this.rules = rules;
    }

    public RuntimeException getException() {
        return exception;
    }

    public void setException(RuntimeException exception) {
        this.exception = exception;
    }

    public static class builder {
        private List<Object> validatableObjects = new ArrayList<>();
        private List<Rule> rules = new ArrayList<>();
        private RuntimeException exception;

        public builder validatableObject(Object object) {
            validatableObjects.add(object);
            return this;
        }

        public builder validatableObject(List<Object> validatableObjects) {
            this.validatableObjects.addAll(validatableObjects);
            return this;
        }

        public builder validatableObject(Object... validatableObjects) {
            this.validatableObjects.addAll(Arrays.asList(validatableObjects));
            return this;
        }

        public builder exception(RuntimeException exception) {
            this.exception = exception;
            return this;
        }

        public builder rule(Rule rule) {
            this.rules.add(rule);
            return this;
        }

        public builder rule(List<Rule> rules) {
            this.rules.addAll(rules);
            return this;
        }

        public builder rule(Rule... rules) {
            this.rules.addAll(Arrays.asList(rules));
            return this;
        }

        public Validator build() {
            Validator validator = new Validator();
            validator.setValidatableObjects(validatableObjects);
            validator.setRules(rules);
            validator.setException(exception);
            return validator;
        }
    }
}
