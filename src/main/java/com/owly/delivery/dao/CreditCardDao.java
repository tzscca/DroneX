package com.owly.delivery.dao;

import com.owly.delivery.entity.CreditCard;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class CreditCardDao {
    @Autowired
    private SessionFactory sessionFactory;

    public CreditCard getCreditCard(long cardNumber) {
        try (Session session = sessionFactory.openSession()) {
            return session.get(CreditCard.class, cardNumber);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }
}
