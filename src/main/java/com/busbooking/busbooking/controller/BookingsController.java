package com.busbooking.busbooking.controller;

import com.busbooking.busbooking.dto.seatBookingDTO;
import com.busbooking.busbooking.entity.*;
import com.busbooking.busbooking.repository.*;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class BookingsController {


    @Autowired
    UserRepository userRepository;
    @Autowired
    BusRepository busRepository;

    @Autowired
    TripRepository tripRepository;
    @Autowired
    SeatRepository seatRepository;


    @Autowired
    SeatBookingRepository seatBookingRepository;
    @Transactional
    @PostMapping("/book-seat")
    public ResponseEntity bookSeat(@RequestBody seatBookingDTO seatInfo){
        Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
        String userName=authentication.getName();
        User dbUser=userRepository.findByUsername(userName);

        Bus bus =busRepository.findById(seatInfo.getBusId()).orElse(null);
        if (dbUser == null || bus == null) {
            return ResponseEntity.badRequest().body("Invalid user or bus");
        }
        Seat seat = seatRepository.findById(seatInfo.getSeatId()).orElse(null);

        if (seat == null) {
            return ResponseEntity.badRequest().body("Seat not found");
        }
        Trip trip=tripRepository.findById(seatInfo.getTripId()).orElse(null);
        if(trip==null) return ResponseEntity.badRequest().body("trip does not exists");
        SeatBooking booking=seatBookingRepository.findBySeatAndTrip(seatInfo.getSeatId(),seatInfo.getTripId());
        if (booking!=null) return ResponseEntity.badRequest().body("Seat is already booked");



        SeatBooking newBooking=new SeatBooking();
        newBooking.setTrip(trip);
        newBooking.setSeat(seat);
        newBooking.setBus(bus);
        newBooking.setUser(dbUser);
        seatBookingRepository.save(newBooking);
        // Do not return the JPA entity:            JSON serialization runs after the transaction ends and
        // would touch lazy collections (User.bookings, Bus.seats, etc.) → LazyInitializationException.
        return ResponseEntity.ok(Map.of("bookingId", newBooking.getId(), "message", "Seat booked"));
    }
    @PostMapping("/removeBooking")
    public ResponseEntity unBookSeat(@RequestBody seatBookingDTO seatInfo){
        Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
        User user=userRepository.findByUsername(authentication.getPrincipal().toString());
        if(user==null){
            return new ResponseEntity("user does not exists",HttpStatusCode.valueOf(500));
        }
        String userId=user.getId();
        SeatBooking seatBooking=seatBookingRepository.findBySeat_IdAndTrip_IdAndUser_UserID(seatInfo.getSeatId(),seatInfo.getTripId(),userId);
        if(seatBooking==null){
            return new ResponseEntity("seat or trip does not exist",HttpStatusCode.valueOf(500));
        }
        seatBookingRepository.deleteById(seatBooking.getId());
        return new ResponseEntity("booking removed",HttpStatusCode.valueOf(200));
    }
}
