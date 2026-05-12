package com.busbooking.busbooking.dto;

import com.busbooking.busbooking.entity.Seat;
import com.busbooking.busbooking.uidtos.UiSeat;
import org.springframework.cglib.core.Local;

import java.time.LocalDate;
import java.util.List;

public class DTO {
    String tripID;
    LocalDate date;

    String busName;

    String busID;

    List<UiSeat> seatList;

    public String getTripID() {
        return tripID;
    }

    public void setTripID(String tripID) {
        this.tripID = tripID;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getBusName() {
        return busName;
    }

    public void setBusName(String busName) {
        this.busName = busName;
    }

    public String getBusID() {
        return busID;
    }

    public void setBusID(String busID) {
        this.busID = busID;
    }

    public List<UiSeat> getSeatList() {
        return seatList;
    }

    public void setSeatList(List<UiSeat> seatList) {
        this.seatList = seatList;
    }
}
