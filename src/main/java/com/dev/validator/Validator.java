package com.dev.validator;

public interface Validator<T> {

    ValidationResult validate(T object);
}