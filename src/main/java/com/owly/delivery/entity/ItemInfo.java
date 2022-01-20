package com.owly.delivery.entity;


import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "ItemInfo")
public class ItemInfo implements Serializable {
    private static final long serialVersionUID = 468874L;

    @Id
    private int itemId;
    private int orderId;
    private int weight;
    private int size;

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    @Override
    public String toString() {
        return "ItemInfo{" +
                "itemId=" + itemId +
                ", orderId=" + orderId +
                ", weight=" + weight +
                ", size=" + size +
                '}';
    }
}
