package com.owly.delivery.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.owly.delivery.entity.*;
import com.owly.delivery.enums.ShipmentStatus;
import com.owly.delivery.service.CreditCardService;
import com.owly.delivery.service.OrderService;
import com.owly.delivery.service.TrackingService;
import com.owly.delivery.service.UserService;
import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.*;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.Calendar;

@Controller
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private UserService userService;

    @Autowired
    private CreditCardService creditCardService;

    @Autowired
    private TrackingService trackingService;

    private final ObjectMapper objectMapper = new ObjectMapper();
    @RequestMapping(value = "/user/orders", method = RequestMethod.GET)
    @ResponseBody
    public List<Orders> getOrderList(HttpServletResponse response) throws IOException {
        String currentUserID = null;
        List<Orders> orders = new ArrayList<Orders>();
        // check userId (email)
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            currentUserID = authentication.getName(); // get email not userId
            System.out.println("currentUserName: " + currentUserID);
            User curUser = userService.getUser(currentUserID);
            orders = orderService.getOrderList(curUser.getUserId());
        }
        else {
            System.out.println("User not logged in, cannot find user name.");
        }
        // setStringActualPickUpTime is not in use
        for (Orders order: orders){
            order.setStringActualPickUpTime();
        }
        return orders;
    }

    @RequestMapping(value = "/order", method = RequestMethod.POST)
    @ResponseBody
    public int order(HttpServletRequest request,
                      HttpServletResponse response) throws IOException {
        try {
            // get order info and creditCard info from front-end
            OrderRequestBody orderRequestBody = objectMapper.readValue(request.getReader(), OrderRequestBody.class);
            Orders order = orderRequestBody.getOrder();
            CreditCard creditCard = orderRequestBody.getCreditCard();

            String currentUserID = null;
            // check userId (email), if not login, error
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            if (!(authentication instanceof AnonymousAuthenticationToken)) {
                currentUserID = authentication.getName(); // get email not userId
                System.out.println("currentUserName: " + currentUserID);
            } else {
                System.out.println("User not logged in, cannot find user name.");
            }


            // verify creditCard
            boolean isValidCreditCard = creditCardService.verifyCreditCard(creditCard);

            // verify the cardNumber and first name (test purpose)
            if (isValidCreditCard) {
                // save to database
                // get the current timestamp = createTime
                Timestamp instant = Timestamp.from(Instant.now());
                System.out.println("Order Create Time " + instant);
                // set to createTime in order
                order.setCreateTime(instant);
                //prepare for calculating actualPickUpTime
                Calendar cal = Calendar.getInstance();
                cal.setTimeInMillis(instant.getTime());

                // check if drone or robot, depends deliveryMethod
                String deliveryMethod = order.getDeliveryMethod();
                // if drone, add 20 mins for actualPickUpTime, another 40 mins for deliveryTime
                if (deliveryMethod.equals("drone")) {
                    cal.add(Calendar.SECOND, 1200);
                    Timestamp actualPickUpTime = new Timestamp(cal.getTime().getTime());
                    cal.add(Calendar.SECOND, 2400);
                    Timestamp deliveryTime = new Timestamp(cal.getTime().getTime());
                    order.setActualPickUpTime(actualPickUpTime);
                    order.setDeliveryTime(deliveryTime);
                }
                // if robot, add 1 hr for actualPickUpTime, add another 2 hrs for deliveryTime
                if (deliveryMethod.equals("robot")){
                    cal.add(Calendar.HOUR, 1);
                    Timestamp actualPickUpTime = new Timestamp(cal.getTime().getTime());
                    cal.add(Calendar.HOUR, 2);
                    Timestamp deliveryTime = new Timestamp(cal.getTime().getTime());
                    order.setActualPickUpTime(actualPickUpTime);
                    order.setDeliveryTime(deliveryTime);
                }

                // get userId from username
                User curUser = userService.getUser(currentUserID);
                order.setUser(curUser);
                int curUserId = curUser.getUserId();
                System.out.println("curUserId = " + curUserId);

                // set order status to "SUBMITTED", set payment to "PAID"
                order.setOrderStatus("SUBMITTED");
                order.setPaymentStatus("PAID");

                // create tracking object
                Tracking curTracking = new Tracking();
                ShipmentStatus status = ShipmentStatus.SUBMITTED;
                curTracking.setShipmentStatus(status);
                System.out.println(ShipmentStatus.SUBMITTED);
                //connect tracking object to order
                order.setTracking(curTracking);
                curTracking.setOrder(order);
                // save order to database
                orderService.saveOrder(order);
                System.out.println("order confirmed");

//                Station curStation = new Station();
//                curStation.setStationName();
//                curTracking.setStation();
//                trackingService.saveTracking(curTracking);
                System.out.println("tracking created, trackingID = " + curTracking.getTrackingId());



                // tes get List<Orders> By User, check functionality purpose
                for (Orders orderItem : curUser.getOrderList()) {
                    System.out.println(orderItem.getOrderId());
                }

                // get orderId for the newly created order object
                System.out.println(order.getOrderId());
                return order.getOrderId();

            } else {
                System.out.println("payment failed, check the input");
                response.setStatus(HttpStatus.UNAUTHORIZED.value()); // response 401
            }
        }
        catch (Exception e){
            System.out.println("Error here");
            e.printStackTrace();
        }
        return 0;
    }


    // new API, get order status by orderId
    @RequestMapping(value = "/getOrderStatus", method = RequestMethod.GET)
    @ResponseBody
    public String getOrderStatus(HttpServletRequest request,@RequestParam(value = "orderId") int orderId, HttpServletResponse response) throws IOException {

        try {
            System.out.println("getOrderStatus API, curOrderId = " + orderId);
            return orderService.getOrderStatus(orderId);
        }   catch (Exception e){
            System.out.println("Error here in getOrderStatus");
            e.printStackTrace();
            response.setStatus(HttpStatus.BAD_REQUEST.value());
        }

        return null;
    }

    // new API: set order status by orderId
    @RequestMapping(value = "/setOrderStatus", method = RequestMethod.POST)
    @ResponseBody
    public void setOrderStatus(@RequestBody Orders order, HttpServletResponse response) throws IOException {

        try {
            int curOrderId = order.getOrderId();
            System.out.println("setOrderStatus API, curOrderId = " + curOrderId);
            String orderStatus = order.getOrderStatus();
            System.out.println("setOrderStatus API, orderStatus = " + orderStatus);
            if (orderService.getOrderStatus(curOrderId) == null) {
                response.setStatus(HttpStatus.BAD_REQUEST.value());
            };
            orderService.setOrderStatus(curOrderId,orderStatus);
        }        catch (Exception e){
            System.out.println("Error here in setOrderStatus");
            e.printStackTrace();
            response.setStatus(HttpStatus.BAD_REQUEST.value());
        }
    }
}
