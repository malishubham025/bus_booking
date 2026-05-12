package com.busbooking.busbooking.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id"
)
public class Bus {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    String id;

    public List<Seat> getSeats() {
        return seats;
    }

    public void setSeats(List<Seat> seats) {
        this.seats = seats;
        for(Seat seat: seats){
            seat.setBus(this);
        }
    }

    @OneToMany(cascade = CascadeType.ALL,orphanRemoval = true,mappedBy = "bus")
    List<Seat> seats=new ArrayList<>();
    @OneToMany(cascade = CascadeType.ALL,orphanRemoval = true,mappedBy = "bus")
    List<SeatBooking> bookings=new ArrayList<>();
    String bus_number;
    String  bus_name;
    String source;
    String destination;

    int total_seats;

    @OneToMany(mappedBy = "bus",cascade = CascadeType.ALL)
    List<Trip>trips=new ArrayList<>();

    public void addTrip(Trip trip){
        trip.setBus(this);
        this.trips.add(trip);
    }

    public List<Trip> getTrips() {
        return trips;
    }

    public void setTrips(List<Trip> trips) {
        this.trips = trips;
        trips.stream().forEach(trip -> {
            trip.setBus(this);
        });
    }

    public String getId() {
        return id;
    }

    public void setId(String bus_id) {
        this.id = bus_id;
    }

    public List<SeatBooking> getBookings() {
        return bookings;
    }

    public void setBookings(List<SeatBooking> bookings) {
        this.bookings = bookings;
        for(SeatBooking booking: bookings){
            booking.setBus(this);
        }
    }

    public String getBus_number() {
        return bus_number;
    }

    public void setBus_number(String bus_number) {
        this.bus_number = bus_number;
    }

    public String getBus_name() {
        return bus_name;
    }

    public void setBus_name(String bus_name) {
        this.bus_name = bus_name;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }



    public int getTotal_seats() {
        return total_seats;
    }

    public void setTotal_seats(int total_seats) {
        this.total_seats = total_seats;
    }

}
