package org.example;

import lombok.Data;
import org.example.rule.Rule;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Data
public class Validator {

    private List<Object> validatableObjects = new ArrayList<>();
    private List<Rule> rules = new ArrayList<>();
    private RuntimeException exception;

    public Validator validatableObject(Object object){
        validatableObjects.add(object);
        return this;
    }

    public Validator validatableObject(List<Object> validatableObjects){
        this.validatableObjects.addAll(validatableObjects);
        return this;
    }

    public Validator validatableObject(Object... validatableObjects){
        this.validatableObjects.addAll(Arrays.asList(validatableObjects));
        return this;
    }

    public Validator exception(RuntimeException exception){
        this.exception = exception;
        return this;
    }
    public Validator rule(Rule rule){
        this.rules.add(rule);
        return this;
    }

    public Validator rule(List<Rule> rules){
        this.rules.addAll(rules);
        return this;
    }

    public Validator rule(Rule... rules){
        this.rules.addAll(Arrays.asList(rules));
        return this;
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
        if (!rule.isValid(o)){
            throw exception;
        }
    }

}
