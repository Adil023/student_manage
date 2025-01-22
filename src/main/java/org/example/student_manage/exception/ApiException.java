package org.example.student_manage.exception;

import org.springframework.http.HttpStatus;

public record ApiException(
        String message,
        HttpStatus httpStatus
) {

}
