package hu.davidhalma.validator;

import hu.davidhalma.validator.rules.GreaterThanZeroRule;
import hu.davidhalma.validator.rules.NotEmptyStringRule;
import hu.davidhalma.validator.rules.ObjectNotNullRule;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MainTest {

    private static final String EXCEPTION_MESSAGE = "Bad param!";

    @Test
    void test_validate_integer_doesNotThrowException() {
        assertDoesNotThrow(() ->
                new Validator.builder()
                        .validatableObject(1)
                        .validatableObject(4)
                        .validatableObject(3, 4, 6, 8, 12)
                        .rule(new ObjectNotNullRule())
                        .rule(new GreaterThanZeroRule())
                        .exception(new IllegalArgumentException(EXCEPTION_MESSAGE))
                        .build().validate());
    }

    @Test
    void test_validate_integer_throwsException() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () ->
                new Validator.builder()
                        .validatableObject(1)
                        .validatableObject(4)
                        .validatableObject(3, 4, 6, 8, 0)
                        .rule(new ObjectNotNullRule(), new GreaterThanZeroRule())
                        .rule(new ObjectNotNullRule()) 
                        //TODO: Handle duplicates
                        .exception(new IllegalArgumentException(EXCEPTION_MESSAGE))
                        .build().validate());
        assertEquals(EXCEPTION_MESSAGE, exception.getMessage());
    }
    
    @Test
    void test_validate_integer_throwsException1() {
        List<Integer> integers = Arrays.asList(432, 54, 877, 12355, -1);
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () ->
                new Validator.builder()
                        .validatableObject(1)
                        .validatableObject(4)
                        .validatableObject(integers)
                        .rule(new ObjectNotNullRule())
                        .rule(new GreaterThanZeroRule())
                        .exception(new IllegalArgumentException(EXCEPTION_MESSAGE))
                        .build().validate());
        assertEquals(EXCEPTION_MESSAGE, exception.getMessage());
    }    
    
    @Test
    void test_validate_object_throwsException() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () ->
                new Validator.builder()
                        .validatableObject("", null)
                        .rule(new NotEmptyStringRule())
                        .exception(new IllegalArgumentException(EXCEPTION_MESSAGE))
                        .build().validate());
        assertEquals(EXCEPTION_MESSAGE, exception.getMessage());
    }

    @Test
    void test_validate_object_doesNotThrowException() {
        assertDoesNotThrow(() ->
                new Validator.builder()
                        .validatableObject("asdf", " fjdsl")
                        .rule(new NotEmptyStringRule())
                        .exception(new IllegalArgumentException(EXCEPTION_MESSAGE))
                        .build().validate());
    }

    @Test
    void test_validate_object_throwsException1() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () ->
                new Validator.builder()
                        .validatableObject("123")
                        .rule(new NotEmptyStringRule())
                        .rule(new GreaterThanZeroRule())
                        .exception(new IllegalArgumentException(EXCEPTION_MESSAGE))
                        .build().validate());
        assertEquals(EXCEPTION_MESSAGE, exception.getMessage());
    }

    @Test
    void test_validate_object_throwsException2() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () ->
                new Validator.builder()
                        .validatableObject("asd", null)
                        .rule(new NotEmptyStringRule())
                        .exception(new IllegalArgumentException(EXCEPTION_MESSAGE))
                        .build().validate());
        assertEquals(EXCEPTION_MESSAGE, exception.getMessage());
    }
}