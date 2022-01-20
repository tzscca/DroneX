package com.owly.delivery.entity;


import com.owly.delivery.enums.VehicleStatus;
import com.owly.delivery.enums.VehicleType;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Vehicle {
    @Id
    private int vehicleID;
    private String vehicleName;
    private VehicleType vehicleType;


    @ManyToOne
    private Station station;
    private VehicleStatus vehicleStatus;
    private long currentLongitude;
    private long currentLatitude;
    private int speed;
    private int capacity;

    public String getVehicleName() {
        return vehicleName;
    }

    public void setVehicleName(String vehicleName) {
        this.vehicleName = vehicleName;
    }

    public VehicleType getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(VehicleType vehicleType) {
        this.vehicleType = vehicleType;
    }

    public Station getStation() {
        return station;
    }

    public void setStation(Station station) {
        this.station = station;
    }

    public VehicleStatus getVehicleStatus() {
        return vehicleStatus;
    }

    public void setVehicleStatus(VehicleStatus vehicleStatus) {
        this.vehicleStatus = vehicleStatus;
    }

    public long getCurrentLongitude() {
        return currentLongitude;
    }

    public void setCurrentLongitude(long currentLongitude) {
        this.currentLongitude = currentLongitude;
    }

    public long getCurrentLatitude() {
        return currentLatitude;
    }

    public void setCurrentLatitude(long currentLatitude) {
        this.currentLatitude = currentLatitude;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    @Override
    public String toString() {
        return "Vehicle{" +
                "vehicleID=" + vehicleID +
                ", vehicleName='" + vehicleName + '\'' +
                ", vehicleType=" + vehicleType +
                ", station=" + station +
                ", vehicleStatus=" + vehicleStatus +
                ", currentLongitude=" + currentLongitude +
                ", currentLatitude=" + currentLatitude +
                ", speed=" + speed +
                ", capacity=" + capacity +
                '}';
    }
}