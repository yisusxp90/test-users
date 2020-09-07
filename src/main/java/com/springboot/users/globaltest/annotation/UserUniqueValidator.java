package com.springboot.users.globaltest.annotation;

import com.springboot.users.globaltest.service.IUsersService;
import org.springframework.beans.factory.annotation.Autowired;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UserUniqueValidator implements ConstraintValidator<UniqueUserEmail,String> {

    @Autowired
    private IUsersService iUsersService;

    @Override
    public void initialize(UniqueUserEmail unique) {
        unique.message();
    }

    @Override
    public boolean isValid(String email, ConstraintValidatorContext context) {
        if(iUsersService != null && iUsersService.findUserByEmail(email).isPresent()) {
            return false;
        }
        return true;
    }
}