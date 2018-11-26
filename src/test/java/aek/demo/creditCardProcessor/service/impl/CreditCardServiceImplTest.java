package aek.demo.creditCardProcessor.service.impl;

import aek.demo.creditCardProcessor.domain.CreditCard;
import aek.demo.creditCardProcessor.service.exception.CreditCardNotValidException;
import aek.demo.creditCardProcessor.service.repository.CreditCardDb;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CreditCardServiceImplTest {

    private static final String cardName = "Dummy Card";
    private static final String cardnumber = "1111 2222 3333 4444";
    private static final double cardLimit = 5000;

    @Mock
    private CreditCardDb creditCardDbMock;

    @InjectMocks
    private CreditCardServiceImpl creditCardServiceImplTest;

    @Test
    public void getAll_ShouldListAllCreditCardsOk() {
        when(creditCardDbMock.getCreditCards())
                .thenReturn(getSingleCardMap());

        assertTrue("Expected card number is not found!",
                creditCardServiceImplTest.getAll().iterator().next().getNumber().equals(cardnumber));
    }

    private Map<String, CreditCard> getSingleCardMap() {
        final Map<String, CreditCard> dummyCardsSet = new HashMap<>();
        dummyCardsSet.put(cardnumber, new CreditCard(cardName, cardnumber, cardLimit));
        return dummyCardsSet;
    }

    @Test
    public void add_ShouldAddOk() {
        assertEquals("Card created!",
                creditCardServiceImplTest.add(new CreditCard(cardName, cardnumber, cardLimit)));
    }

    @Test(expected = CreditCardNotValidException.class)
    public void add_ShouldThrowError_IfCardNumberIsNotValidLuhn10() {
        try {
            creditCardServiceImplTest.add(new CreditCard(cardName, "101010101", cardLimit));
        } catch (CreditCardNotValidException ccnve) {
            assertEquals("Credit card number is invalid!", ccnve.getMessage());
            throw ccnve;
        }
    }

    @Test(expected = CreditCardNotValidException.class)
    public void add_ShouldThrowError_IfCardNumberHasMoreThan19Digits() {
        try {
            creditCardServiceImplTest.add(new CreditCard(cardName, "12345678901234567890", cardLimit));
        } catch (CreditCardNotValidException ccnve) {
            assertEquals("Credit card number has more than 19 digits!", ccnve.getMessage());
            throw ccnve;
        }
    }

    @Test(expected = CreditCardNotValidException.class)
    public void add_ShouldAddOk_IfCardNumberHasNonDigits() {
        assertEquals("Card created!",
                creditCardServiceImplTest.add(new CreditCard(cardName, "1155aabb??--||", cardLimit)));
    }
}