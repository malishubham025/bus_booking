package com.busbooking.busbooking.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;

@Entity
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id"
)
public class SeatBooking {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    String id;
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "seat_id")
    Seat seat;



    @ManyToOne(cascade = CascadeType.PERSIST)
            @JoinColumn(name = "trip_id")
    Trip trip;

    @ManyToOne
    @JoinColumn(name = "bus_id")
    Bus bus;

    @ManyToOne()
    @JoinColumn(name = "user_id")
    User user;

    public Bus getBus() {
        return bus;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setBus(Bus bus) {
        this.bus = bus;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Seat getSeat() {
        return seat;
    }

    public void setSeat(Seat seat) {
        this.seat = seat;
    }


    public Trip getTrip() {
        return trip;
    }

    public void setTrip(Trip trip) {
        this.trip = trip;
    }
}
