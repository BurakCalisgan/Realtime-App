package com.realtime.api.realtimeapp.config;

import com.realtime.api.realtimeapp.exception.BaseApiException;
import com.realtime.api.realtimeapp.exception.general.ApiError;
import com.realtime.api.realtimeapp.exception.general.ValidationError;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ClassUtils;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;


@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
@RequiredArgsConstructor
@Slf4j
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers,
                                                                  HttpStatusCode status,
                                                                  WebRequest request) {
        ApiError apiError = new ApiError();
        apiError.setStatus(status.toString());
        apiError.setMessage("Validation Failed");
        apiError.setCode("Validation Error");
        apiError.setTimestamp(new Date().getTime());

        List<ValidationError> details = new ArrayList<>();

        ex.getBindingResult().getAllErrors().forEach((objectError -> {
            ValidationError validationError = new ValidationError();
            validationError.setField(((FieldError) objectError).getField());
            validationError.setError(objectError.getCode());
            validationError.setMessage(((FieldError) objectError).getField() + " " + objectError.getDefaultMessage());
            details.add(validationError);
        }));
        apiError.setDetails(details);

        return ResponseEntity.status(status).body(apiError);
    }

    @ExceptionHandler(Exception.class)
    protected ResponseEntity<Object> handleAllExceptions(
            Exception ex) {

        ApiError apiError = new ApiError();
        apiError.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.toString());
        apiError.setMessage(ex.getLocalizedMessage());
        apiError.setCode("InternalServerError");
        apiError.setTimestamp(new Date().getTime());
        log.error("Error on", ex);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(apiError);
    }

    @ExceptionHandler(BaseApiException.class)
    protected ResponseEntity<Object> handleEntityException(BaseApiException ex, Locale locale) {
        ApiError apiError = new ApiError();
        apiError.setStatus(ex.getStatus().toString());
        apiError.setCode(ClassUtils.getShortClassName(ex, "false"));
        apiError.setTimestamp(new Date().getTime());
        apiError.setMessage(ex.getMessage());

        return ResponseEntity.status(ex.getStatus()).body(apiError);
    }
}
