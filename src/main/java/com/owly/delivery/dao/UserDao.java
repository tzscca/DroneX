package com.owly.delivery.dao;

import com.owly.delivery.entity.Authorities;
import com.owly.delivery.entity.Orders;
import com.owly.delivery.entity.User;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class UserDao {
    @Autowired
    private SessionFactory sessionFactory;

    public void signUp(User user) throws Exception {
        Authorities authorities = new Authorities();
        authorities.setAuthorities("ROLE_USER");
        authorities.setEmail(user.getEmail());

        Session session = null;
        try {
            session = sessionFactory.openSession();
            session.beginTransaction();
            session.save(authorities);
            session.save(user);
            session.getTransaction().commit();

        } catch (Exception ex) {
            ex.printStackTrace();
            session.getTransaction().rollback();
            throw new Exception(ex);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    public User getUser(String email) {
        User user = null;
        try (Session session = sessionFactory.openSession()) {
            Criteria criteria = session.createCriteria(User.class);
            user = (User) criteria.add(Restrictions.eq("email", email))
                    .uniqueResult();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return user;
    }

    public User getUserById(int userId) {
        User user = null;
        try  {
            Session session = sessionFactory.openSession();
            user = session.get(User.class, userId);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return user;
    }


    public User editUser(User user) {
        Session session = null;
        int userId = user.getUserId();
        user.setEnabled(true);
        try {
            session = sessionFactory.openSession();
            session.beginTransaction();
            session.update(user);
            session.getTransaction().commit();

        } catch (Exception ex) {
            ex.printStackTrace();
            if (session != null) session.getTransaction().rollback();
        }

        return getUserById(userId);
    }

    public List<Orders> getOrderList(int userId) {
        List<Orders> orders = new ArrayList<>();
        try (Session session = sessionFactory.openSession()) {
            Criteria criteria = session.createCriteria(Orders.class);
            List<Orders> all_orders = criteria.list();
            for(Orders order: all_orders) {
                User user = order.getUser();
                System.out.println("userdao:" + user.getUserId());
                if(user.getUserId() == userId) {
                    orders.add(order);
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return orders;

    }
}
