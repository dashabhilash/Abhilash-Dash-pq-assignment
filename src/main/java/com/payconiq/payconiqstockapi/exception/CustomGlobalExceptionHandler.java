package com.payconiq.payconiqstockapi.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

@Slf4j
@ControllerAdvice
public class CustomGlobalExceptionHandler extends ResponseEntityExceptionHandler {

    /**
     * Handle Exception, handle generic Exception class
     *
     * @param e Exception
     * @return the StockApiError object
     */

    @ExceptionHandler(value = Exception.class)
    protected ResponseEntity<Object> defaultErrorHandler(Exception e) {
        log.error("Unexpected exception", e);
        return buildResponseEntity(new StockApiError(INTERNAL_SERVER_ERROR, e.getMessage()));
    }

    /**
     * Handle MissingServletRequestParameterException. Triggered when a 'required' request parameter is missing.
     *
     * @param e       MissingServletRequestParameterException
     * @param headers HttpHeaders
     * @param status  HttpStatus
     * @param request WebRequest
     * @return the StockApiError object
     */
    @Override
    protected ResponseEntity<Object> handleMissingServletRequestParameter(
            MissingServletRequestParameterException e, HttpHeaders headers,
            HttpStatus status, WebRequest request) {
        String error = e.getParameterName() + " parameter is missing";
        return buildResponseEntity(new StockApiError(BAD_REQUEST, error));
    }


    /**
     * Handle StockSystemException, handle system issues
     *
     * @param e StockSystemException
     * @return the StockApiError object
     */
    @ExceptionHandler({StockSystemException.class})
    public ResponseEntity<Object> handleMediaSystemException(StockSystemException e) {
        log.error(e.getMessage(), e);
        return buildResponseEntity(new StockApiError(INTERNAL_SERVER_ERROR, e.getMessage()));
    }

    private ResponseEntity<Object> buildResponseEntity(StockApiError stockApiError) {
        return new ResponseEntity<>(stockApiError, stockApiError.getStatus());
    }
}
