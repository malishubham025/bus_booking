package com.busbooking.busbooking.dto;

import com.busbooking.busbooking.entity.Seat;

import java.time.LocalDate;
import java.util.List;

public record BusesDTO(
        String busId,
        String busName,
        String busNumber,
        String destination,
        String source,
        int totalSeats,
        LocalDate departureTime,
        String tripId,
        Long bookedSeatsCount
) {}
