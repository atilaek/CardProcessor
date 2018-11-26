package aek.demo.creditCardProcessor.controller;

import aek.demo.creditCardProcessor.domain.CreditCard;
import aek.demo.creditCardProcessor.service.CreditCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Set;

/**
 * CreditCardServiceController for REST API that uses @{@link CreditCardService} for operations.
 *
 * @author Atila Ekimci
 */
@Controller
@RequestMapping("/creditCard")
public class CreditCardServiceController {

    @Autowired
    private CreditCardService creditCardService;

    /**
     * Returns all credit cards.
     *
     * @return @{@link ResponseEntity} that has the a confirmation string.
     */
    @GetMapping(path = "/getAll")
    public ResponseEntity<Set<CreditCard>> getAll() {
        return ResponseEntity.ok(creditCardService.getAll());
    }

    /**
     * Returns adding process status within response status.
     *
     * @param creditCard creditCard with details
     * @return @{@link ResponseEntity} that has the result of credit card adding.
     */
    @PostMapping
    public ResponseEntity<String> add(@RequestBody final CreditCard creditCard) {
        return ResponseEntity.ok(String.valueOf(creditCardService.add(creditCard)));
    }

}
