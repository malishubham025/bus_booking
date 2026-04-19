package com.busbooking.busbooking.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;

import java.sql.Time;

@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "booking_id"
)
@Entity
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    String booking_id;

    Time booking_time;
    boolean status;

    @ManyToOne
    User user;

    @ManyToOne
    Bus bus;

    public String getBooking_id() {
        return booking_id;
    }

    public void setBooking_id(String booking_id) {
        this.booking_id = booking_id;
    }

    public Time getBooking_time() {
        return booking_time;
    }

    public void setBooking_time(Time booking_time) {
        this.booking_time = booking_time;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Bus getBus() {
        return bus;
    }

    public void setBus(Bus bus) {
        this.bus = bus;
    }
}
