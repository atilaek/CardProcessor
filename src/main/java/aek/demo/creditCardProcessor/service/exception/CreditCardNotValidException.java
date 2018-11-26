package aek.demo.creditCardProcessor.service.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Exception throwing class for giving controlled exceptions
 * if credit card details are not valid.<p>
 *
 * @author Atila Ekimci
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class CreditCardNotValidException extends RuntimeException {

    public CreditCardNotValidException(String message) {
        super(message);
    }

}
