package com.github.sergeyskotarenko.contacts.api.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST,
        reason = "Given pattern is incorrect")
public class BadRequestException extends RuntimeException {
}
