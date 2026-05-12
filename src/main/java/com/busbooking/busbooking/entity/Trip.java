package com.busbooking.busbooking.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id"
)
public class Trip {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    String id;

    LocalDate departureTime;

    @ManyToOne()
    @JoinColumn(name = "bus_id")
    Bus bus;



    @OneToMany(mappedBy = "trip")
    List<SeatBooking>seatBookings=new ArrayList<>();

    public List<SeatBooking> getSeatBookings() {
        return seatBookings;
    }

    public void setSeatBookings(List<SeatBooking> seatBookings) {
        this.seatBookings = seatBookings;
    }

    public void addSeatBooking(SeatBooking seatBooking){
        this.seatBookings.add(seatBooking);
        seatBooking.setTrip(this);
    }
    public String getId() {
        return id;
    }



    public void setId(String id) {
        this.id = id;
    }

    public LocalDate getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(LocalDate departureTime) {
        this.departureTime = departureTime;
    }

    public Bus getBus() {
        return bus;
    }

    public void setBus(Bus bus) {
        this.bus = bus;
    }
}
