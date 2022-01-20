package com.owly.delivery.service;


import com.owly.delivery.dao.CreditCardDao;
import com.owly.delivery.dao.OrderDao;
import com.owly.delivery.entity.CreditCard;
import com.owly.delivery.entity.Orders;
import com.owly.delivery.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class CreditCardService {

//    @Autowired
//    private UserService userService;

    @Autowired
    private CreditCardDao creditCardDao;

    // get credit card info by cardNumber
    public CreditCard getCreditCard(long cardNumber ) {

        return creditCardDao.getCreditCard(cardNumber);

    }

    // verify credit card info
    // get credit card by card number
    // verify card by first last names, cvv, expirationDate
    // if fail, return false
    public boolean verifyCreditCard(CreditCard creditCard) {

        if (creditCard == null) {
            return false;
        }

        // look up the card
        long creditCardNumber = (long) creditCard.getCardNumber();
        CreditCard getCreditCardByCardNumber = creditCardDao.getCreditCard(creditCardNumber);
        if (getCreditCardByCardNumber == null) {
            System.out.println("not get card");
            return false;
        }

        // check firstName, lastName, cvv, expirationDate
        // read the input creditCard data
        String inputFirstName = creditCard.getFirstName();
        String inputLastName = creditCard.getLastName();
        int inputCVV = creditCard.getCvv();
        String inputExpirationDate = creditCard.getExpirationDate();

        // extract the database card info
        String dbFirstName = getCreditCardByCardNumber.getFirstName();
        String dbLastName = getCreditCardByCardNumber.getLastName();
        int dbCVV = getCreditCardByCardNumber.getCvv();
        String dbExpirationDate = getCreditCardByCardNumber.getExpirationDate();

        return inputFirstName.equals(dbFirstName) && inputLastName.equals(dbLastName) && (inputCVV == dbCVV) && inputExpirationDate.equals(dbExpirationDate);
    }
}
