package com.springboot.users.globaltest.validation;

import org.springframework.validation.BindingResult;

import java.util.Map;

public interface IValidateService {

    Map<String, Object> validateCreate(Map<String, Object> errors, BindingResult result);
}
