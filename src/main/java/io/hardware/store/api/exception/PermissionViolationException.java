package io.hardware.store.api.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.FORBIDDEN)
public class PermissionViolationException extends RuntimeException{
    public PermissionViolationException(Long id) {
        super(String.format("User with id: %s has no permission to perform requested operation.", id));
    }
}
