package aek.demo.creditCardProcessor.service.repository;

import aek.demo.creditCardProcessor.domain.CreditCard;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

/**
 * The class that is representative of credit cards database.
 *
 * @author Atila Ekimci
 */
@Repository
public class CreditCardDb {

    private static Map<String, CreditCard> creditCards = new HashMap<>();

    static {
        creditCards.put("1111 2222 3333 4444", new CreditCard("James", "1111 2222 3333 4444", 2000));
        creditCards.put("4444 3333 2222 1111", new CreditCard("John", "4444 3333 2222 1111", 3000));
        creditCards.put("1111 3333 2222 4444", new CreditCard("Jatila", "1111 3333 2222 4444", 5000));
    }

    public Map<String, CreditCard> getCreditCards() {
        return creditCards;
    }
}
