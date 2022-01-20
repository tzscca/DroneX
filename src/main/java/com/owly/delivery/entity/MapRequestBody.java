package com.owly.delivery.entity;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

// this file is not on github
public class MapRequestBody {
    public String fromAddress;
    public String toAddress;

    public String getFromAddress() {
        return fromAddress;
    }

    public String getToAddress() {
        return toAddress;
    }

    @JsonCreator
    public MapRequestBody(@JsonProperty("fromAddress") String fromAddress, @JsonProperty("toAddress") String toAddress){
        this.fromAddress = fromAddress;
        this.toAddress = toAddress;
    }
}
