package com.busbooking.busbooking.controller;

import com.busbooking.busbooking.dto.TripDTO;
import com.busbooking.busbooking.entity.Bus;
import com.busbooking.busbooking.entity.Trip;
import com.busbooking.busbooking.repository.BusRepository;
import com.busbooking.busbooking.repository.TripRepository;
import jakarta.persistence.GeneratedValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
public class TripController {

    @Autowired
    TripRepository repository;

    @Autowired
    BusRepository busRepository;

    @PostMapping("/buses2")
    ResponseEntity addTrip(@RequestBody TripDTO tripDTO){
        Trip trip=repository.findByBus_IdAndDepartureTime(tripDTO.getBusId(),tripDTO.getDate());
        if(trip!=null){
            return  new ResponseEntity("trip already exist",HttpStatusCode.valueOf(400));
        }
        Bus bus=busRepository.findById(tripDTO.getBusId()).orElse(null);
        if (bus==null) return  new ResponseEntity("bus does not exists",HttpStatusCode.valueOf(400));
        Trip trip1=new Trip();
        trip1.setDepartureTime(tripDTO.getDate());
        trip1.setBus(bus);
        bus.addTrip(trip1);
        busRepository.save(bus);
        return  new ResponseEntity(HttpStatusCode.valueOf(200));
    }
    @GetMapping("/viewTrips/{bus_id}")
    ResponseEntity viewTrips(@PathVariable String bus_id){
        List<Trip> trips=repository.findByBus_Id(bus_id);
        return  new ResponseEntity(trips,HttpStatusCode.valueOf(200));
    }

    @GetMapping("/available-seats")
    public ResponseEntity getCountByTripId(){
        List<Object>obj=repository.getCountPerTrip();
        return new ResponseEntity(HttpStatusCode.valueOf(200));
    }
}
