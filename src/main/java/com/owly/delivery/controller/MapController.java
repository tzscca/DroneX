package com.owly.delivery.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.maps.GeoApiContext;
import com.google.maps.GeocodingApi;
import com.google.maps.model.GeocodingResult;
import com.google.maps.model.LatLng;
import com.owly.delivery.entity.CreditCard;
import com.owly.delivery.entity.MapRequestBody;
import com.owly.delivery.entity.OrderRequestBody;
import com.owly.delivery.entity.Orders;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

// this file is not in github management
@Controller
public class MapController {

    private final ObjectMapper objectMapper = new ObjectMapper();

    // input origin and destination address and return price for robot and drone
    @RequestMapping(value = "quote", method = RequestMethod.GET)
    // @ResponseBody
    public void getQuote(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            // get order info and creditCard info from front-end
            MapRequestBody mapRequestBody = objectMapper.readValue(request.getReader(), MapRequestBody.class);
            String fromAddress = mapRequestBody.getFromAddress();
            String toAddress = mapRequestBody.getToAddress();
            System.out.println("fromAddress " + fromAddress);
            System.out.println("toAddress   " + toAddress);

            GeoApiContext context = new GeoApiContext.Builder()
                    .apiKey("AIzaSyAPerW4DlNN3JvRMUkGesnBFi6HBwpMbDs")
                    .build();
            GeocodingResult[] results =  GeocodingApi.geocode(context, fromAddress).await();

            // get location info
            LatLng latLng = results[0].geometry.location;

            // sperate lat and lng info
            double lat = latLng.lat;
            double lng = latLng.lng;
            System.out.println("lat=" + lat);
            System.out.println("lng=" + lng);

            //
              // use Haversince formula to calculate
              //determine the length of the polyline, the straight distance between the two markers
              // accept two markers objects and returns the distance between them in miles
             // before applying haversine formula



            //Gson gson = new GsonBuilder().setPrettyPrinting().create();
            // System.out.println(gson.toJson(results[0].addressComponents));
            //System.out.println(gson.toJson(results[0].geometry));

            // Invoke .shutdown() after your application is done making requests
            context.shutdown();

        } catch (Exception e) {
            System.out.println("Error here");
            e.printStackTrace();
        }
    }

}