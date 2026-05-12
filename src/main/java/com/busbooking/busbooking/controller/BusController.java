package com.busbooking.busbooking.controller;


import com.busbooking.busbooking.dto.BusesDTO;
import com.busbooking.busbooking.dto.DTO;
import com.busbooking.busbooking.dto.TripDTO;
import com.busbooking.busbooking.dto.seatPerBus;
import com.busbooking.busbooking.entity.Bus;
import com.busbooking.busbooking.entity.Seat;
import com.busbooking.busbooking.entity.Trip;
import com.busbooking.busbooking.repository.BusRepository;
import com.busbooking.busbooking.repository.SeatRepository;
import com.busbooking.busbooking.repository.TripRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

@RestController
public class BusController {

    @Autowired
    BusRepository repository;
    @Autowired
    TripRepository tripRepository;

    @Autowired
    SeatRepository seatRepository;
    @PostMapping("/add-bus")
    public ResponseEntity addBus(@RequestBody  Bus bus){
        try{
            List<Seat> seats = new ArrayList<>();
            for (int i = 1; i <= bus.getTotal_seats(); i++) {
                Seat seat = new Seat();
                seat.setSeatNumber(i);

                seat.setBus(bus);
                seats.add(seat);
            }
            bus.setSeats(seats);
            repository.save(bus);
            return new ResponseEntity("bus added seats",HttpStatusCode.valueOf(200));
        }
        catch (Exception e){
            return new ResponseEntity(e.getMessage(), HttpStatusCode.valueOf(500));
        }
    }

    @PostMapping("/remove-bus/{busID}")
    public ResponseEntity removeBus(@PathVariable String busID){
        try{
            repository.deleteById(busID);
            return new ResponseEntity("bus added",HttpStatusCode.valueOf(200));
        }
        catch (Exception e){
            return new ResponseEntity(e.getMessage(), HttpStatusCode.valueOf(500));
        }
    }

    @GetMapping("/buses")
    public ResponseEntity getAllBuses(
            @RequestParam(defaultValue = "0") int pageNumber,
            @RequestParam(defaultValue = "10") int pageSize
    ){

        List<BusesDTO> abc=repository.getBusesByTrips();
        List<Object>bookedSeats=tripRepository.getCountPerTrip();


        return new ResponseEntity(abc,HttpStatusCode.valueOf(200));
    }

    @GetMapping("getSeats")
    public ResponseEntity<?> getSeatsperBus(
            @RequestParam("busId") String busId,
            @RequestParam("tripId") String tripId
    ){

       List<seatPerBus> obj=seatRepository.getSeatsByTripAndBus(busId,tripId);

        return ResponseEntity.ok(obj);
    }

}
