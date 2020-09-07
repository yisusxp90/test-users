package com.springboot.users.globaltest.validation.impl;

import com.springboot.users.globaltest.validation.IValidateService;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import java.util.Map;

@Service
public class ValidateServiceImpl implements IValidateService {

    @Override
    public Map<String, Object> validateCreate(Map<String, Object> errors, BindingResult result) {
        result.getFieldErrors().forEach(error -> {
            errors.put(error.getField(), error.getField() + " " + error.getDefaultMessage());
        });
        return errors;
    }
}
