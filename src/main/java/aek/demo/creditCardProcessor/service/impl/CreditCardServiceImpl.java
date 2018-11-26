package aek.demo.creditCardProcessor.service.impl;

import aek.demo.creditCardProcessor.domain.CreditCard;
import aek.demo.creditCardProcessor.service.CreditCardService;
import aek.demo.creditCardProcessor.service.exception.CreditCardNotValidException;
import aek.demo.creditCardProcessor.service.repository.CreditCardDb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;


/**
 * CreditCardService for credit card related operations.<p>
 * Uses @{@link CreditCardDb} as repository and performs operations
 * such as add.
 *
 * @author Atila Ekimci
 */
@Service
public class CreditCardServiceImpl implements CreditCardService {

    @Autowired
    private CreditCardDb creditCardDb;

    public static boolean validateCardNumber(String ccNumber) {
        int sum = 0;
        boolean alternate = false;
        for (int i = ccNumber.length() - 1; i >= 0; i--) {
            int n = Integer.parseInt(ccNumber.substring(i, i + 1));
            if (alternate) {
                n *= 2;
                if (n > 9) {
                    n = (n % 10) + 1;
                }
            }
            sum += n;
            alternate = !alternate;
        }
        return (sum % 10 == 0);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Set<CreditCard> getAll() {
        return new HashSet<>(creditCardDb.getCreditCards().values());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String add(CreditCard creditCard) throws CreditCardNotValidException {
        String result = "";
        String cardNumberStringCleaned = creditCard.getNumber().replaceAll("[^0-9]", "");
        if (cardNumberStringCleaned.length() >= 20) {
            throw new CreditCardNotValidException("Credit card number has more than 19 digits!");
        } else if (creditCardDb.getCreditCards().get(cardNumberStringCleaned) != null) {
            throw new CreditCardNotValidException("A credit card with the same number already exists!");
        } else {
            try {
                if (!validateCardNumber(cardNumberStringCleaned)) {
                    throw new CreditCardNotValidException("Credit card number is invalid!");
                } else if (creditCard.getBalance() != 0) {
                    result += "New card balance will be set to 0! ";
                }
                creditCardDb.getCreditCards().put(creditCard.getNumber(), creditCard);
                result += "Card created!";
                return result;
            } catch (NumberFormatException nfr) {
                throw new CreditCardNotValidException("Credit card number in not only digits!" + nfr.getMessage());
            }
        }
    }
}
