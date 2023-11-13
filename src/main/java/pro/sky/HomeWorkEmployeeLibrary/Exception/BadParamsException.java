package pro.sky.HomeWorkEmployeeLibrary.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class BadParamsException extends RuntimeException{

    public BadParamsException(String message) {
        super(message);
    }

    public BadParamsException(Throwable cause) {
        super(cause);
    }

    public BadParamsException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

}
