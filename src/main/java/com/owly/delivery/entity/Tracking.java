package com.owly.delivery.entity;


import com.owly.delivery.enums.ShipmentStatus;

import javax.persistence.*;


@Entity
public class Tracking {
    @Id
    @GeneratedValue
    private int trackingId;

    @Enumerated(EnumType.STRING)
    private ShipmentStatus shipmentStatus;

    @OneToOne
    private Orders order;

    @ManyToOne
    private Station station;

    @ManyToOne
    private Vehicle vehicle;

    public Orders getOrder() {
        return order;
    }

    public void setOrder(Orders order) {
        this.order = order;
    }

    public ShipmentStatus getShipmentStatus() {
        return shipmentStatus;
    }

    public void setShipmentStatus(ShipmentStatus shipmentStatus) {
        this.shipmentStatus = shipmentStatus;
    }

    public Station getStation() {
        return station;
    }

    public void setStation(Station station) {
        this.station = station;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public int getTrackingId() {
        return trackingId;
    }

    public void setTrackingId(int trackingId) {
        this.trackingId = trackingId;
    }

    @Override
    public String toString() {
        return "Tracking{" +
                "trackingId=" + trackingId +
                ", shipmentStatus=" + shipmentStatus +
                ", order=" + order +
                ", station=" + station +
                ", vehicle=" + vehicle +
                '}';
    }
}
