package aek.demo.creditCardProcessor.service;

import aek.demo.creditCardProcessor.domain.CreditCard;

import java.util.Set;

/**
 * CreditCardService for credit cards related operations.
 *
 * @author Atila Ekimci
 */
public interface CreditCardService {

    /**
     * Returns all existing credit cards.
     *
     * @return all existing credit cards.
     */
    Set<CreditCard> getAll();

    /**
     * Adds a new card to the fictional DB.
     *
     * @param creditCard the credit card object.
     * @return status of adding credit card.
     */
    String add(final CreditCard creditCard);

}
