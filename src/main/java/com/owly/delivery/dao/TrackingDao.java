package com.owly.delivery.dao;

import com.owly.delivery.entity.Orders;
import com.owly.delivery.entity.Tracking;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.jvnet.fastinfoset.sax.RestrictedAlphabetContentHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public class TrackingDao {
    @Autowired
    SessionFactory sessionFactory;

    public Tracking getTrackingByOrderID(int orderID){
        Tracking tracking = null;
        try (Session session = sessionFactory.openSession()){
            Criteria criteria = session.createCriteria(Tracking.class);
            tracking =
                    (Tracking) criteria.add(Restrictions.eq("order.id", orderID)).uniqueResult();
        }
        catch (Exception ex){
            ex.printStackTrace();
        }
        return tracking;
    }

    // add save tracking
    public void save(Tracking tracking) {
//        Authorities authorities = new Authorities();
//        authorities.setAuthorities("ROLE_USER");
//        authorities.setEmail(user.getEmail());

        Session session = null;
        try {
            session = sessionFactory.openSession();
            session.beginTransaction();
            session.save(tracking);
            session.getTransaction().commit();

        } catch (Exception ex) {
            ex.printStackTrace();
            session.getTransaction().rollback();
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }
}
