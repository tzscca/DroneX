package com.owly.delivery.service;

import com.owly.delivery.dao.TrackingDao;
import com.owly.delivery.entity.Orders;
import com.owly.delivery.entity.Tracking;
import com.owly.delivery.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TrackingService {
    @Autowired
    TrackingDao trackingDao;

    // get tracking by orderID
    public Tracking getTracking (int orderID) {

        Tracking tracking = trackingDao.getTrackingByOrderID(orderID);
        return tracking;
    }

    // save tracking object to database
    public void saveTracking(Tracking tracking) {

//        Authentication loggedInUser = SecurityContextHolder.getContext().getAuthentication();
//        String username = loggedInUser.getName();
//        User user = userService.getUser(username);

        trackingDao.save(tracking);

    }
}
