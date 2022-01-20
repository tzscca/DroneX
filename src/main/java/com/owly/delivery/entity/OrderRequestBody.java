package com.owly.delivery.entity;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class OrderRequestBody {
    public Orders order;
    public CreditCard creditCard;

    public Orders getOrder() {
        return order;
    }

    public CreditCard getCreditCard() {
        return creditCard;
    }

    @JsonCreator
    public OrderRequestBody(@JsonProperty("order") Orders order, @JsonProperty("credit_card") CreditCard creditCard){
        this.order = order;
        this.creditCard = creditCard;
    }
}
