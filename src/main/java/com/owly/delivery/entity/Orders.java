package com.owly.delivery.entity;

import java.sql.Timestamp;
import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "Orders")
public class Orders implements Serializable {
    private static final long serialVersionUID = 145823L;

    @Id
    @GeneratedValue
    private int orderId;
    //private int userID;
    private String recipientName;
    private String fromAddress;
    private String toAddress;
    private Timestamp actualPickUpTime;
    private Timestamp createTime;
    private Timestamp departTime;
    private Timestamp desiredPickedUpTime;
    private Timestamp deliveryTime;
    private double totalCost;
    private String paymentStatus;
    private String Review;
    private String orderStatus;
    private String weight; // new field
    private String size; // new field
    private String senderName; // new field
    private String estimatedDeliveryTime; // new field
    private String senderPhoneNumber; // new field
    private String recipientPhoneNumber; // new field
    private String deliveryMethod; // new field
    private String stringActualPickUpTime;

    // define FK
    @ManyToOne
    @JsonIgnore
    private User user;

    @OneToOne(cascade = CascadeType.ALL)
    @JsonIgnore
    Tracking tracking;


    public String getStringActualPickUpTime() {
        return stringActualPickUpTime;
    }

    public void setStringActualPickUpTime() {
        this.stringActualPickUpTime =
                new SimpleDateFormat("MM/dd/yyyy HH:mm").format(actualPickUpTime);;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }


    public String getRecipientName() {
        return recipientName;
    }

    public void setRecipientName(String recipientName) {
        this.recipientName = recipientName;
    }

//    public int getUserID() {
//        return userID;
//    }
//
//    public void setUserID(int userID) {
//        this.userID = userID;
//    }

    public String getFromAddress() {
        return fromAddress;
    }

    public void setFromAddress(String fromAddress) {
        this.fromAddress = fromAddress;
    }

    public String getToAddress() {
        return toAddress;
    }

    public void setToAddress(String toAddress) {
        this.toAddress = toAddress;
    }

    public Timestamp getActualPickUpTime() {
        return actualPickUpTime;
    }

    public void setActualPickUpTime(Timestamp actualPickUpTime) {
        this.actualPickUpTime = actualPickUpTime;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public Timestamp getDepartTime() {
        return departTime;
    }

    public void setDepartTime(Timestamp departTime) {
        this.departTime = departTime;
    }

    public Timestamp getDesiredPickedUpTime() {
        return desiredPickedUpTime;
    }

    public void setDesiredPickedUpTime(Timestamp desiredPickedUpTime) {
        this.desiredPickedUpTime = desiredPickedUpTime;
    }

    public Timestamp getDeliveryTime() {
        return deliveryTime;
    }

    public void setDeliveryTime(Timestamp deliveryTime) {
        this.deliveryTime = deliveryTime;
    }

    public double getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(double totalCost) {
        this.totalCost = totalCost;
    }


    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public String getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(String paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public String getReview() {
        return Review;
    }

    public void setReview(String review) {
        Review = review;
    }

    public User getUser(){
        return user;
    }

    public void setUser(User user){
        this.user = user;
    }


    public String getSenderName() {
        return senderName;
    }

    public void setSenderName(String senderName) {
        this.senderName = senderName;
    }

    public String getEstimatedDeliveryTime() {
        return estimatedDeliveryTime;
    }

    public void setEstimatedDeliveryTime(String estimatedDeliveryTime) {
        this.estimatedDeliveryTime = estimatedDeliveryTime;
    }

    public Tracking getTracking() {
        return tracking;
    }

    public void setTracking(Tracking tracking) {
        this.tracking = tracking;
    }

    public String getSenderPhoneNumber() {
        return senderPhoneNumber;
    }

    public void setSenderPhoneNumber(String senderPhoneNumber) {
        this.senderPhoneNumber = senderPhoneNumber;
    }

    public String getRecipientPhoneNumber() {
        return recipientPhoneNumber;
    }

    public void setRecipientPhoneNumber(String recipientPhoneNumber) {
        this.recipientPhoneNumber = recipientPhoneNumber;
    }

    public String getDeliveryMethod() {
        return deliveryMethod;
    }

    public void setDeliveryMethod(String deliveryMethod) {
        this.deliveryMethod = deliveryMethod;
    }


    @Override
    public String toString() {
        return "Orders{" +
                "orderId=" + orderId +
                ", recipientName='" + recipientName + '\'' +
                ", fromAddress='" + fromAddress + '\'' +
                ", toAddress='" + toAddress + '\'' +
                ", actualPickUpTime=" + actualPickUpTime +
                ", createTime=" + createTime +
                ", departTime=" + departTime +
                ", desiredPickedUpTime=" + desiredPickedUpTime +
                ", deliveryTime=" + deliveryTime +
                ", totalCost=" + totalCost +
                ", paymentStatus='" + paymentStatus + '\'' +
                ", Review='" + Review + '\'' +
                ", orderStatus='" + orderStatus + '\'' +
                ", weight=" + weight +
                ", size=" + size +
                ", senderName=" + senderName +
                ", estimatedDeliveryTime=" + estimatedDeliveryTime +
                ", senderPhoneNumber=" + senderPhoneNumber +
                ", recipientPhoneNumber=" + recipientPhoneNumber +
                ", deliveryMethod=" + deliveryMethod +
                '}';
    }

}
