package com.alby.roleservice.serviceimpl;

import com.alby.roleservice.service.ValidationService;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class ValidationServiceImpl implements ValidationService {

    private final Validator validator;

    @Override
    public void validate(Object request) {
        Set<ConstraintViolation<Object>> constraintViolations = validator.validate(request);
        if (!constraintViolations.isEmpty()) {
            StringBuilder message = new StringBuilder();
            for (ConstraintViolation<Object> violation: constraintViolations) {
                message.append(new StringBuilder()
                                .append(violation.getPropertyPath()
                                        .toString()
                                        .toLowerCase())
                                .append(" ")
                                .append(violation.getMessage()))
                        .append("\n");
            }

            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, message.toString());
        }
    }
    
}
