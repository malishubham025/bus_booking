package com.busbooking.busbooking.repository;

import com.busbooking.busbooking.dto.seatPerBus;
import com.busbooking.busbooking.entity.Seat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SeatRepository extends JpaRepository<Seat,String> {


    @Query("""
    
    select new com.busbooking.busbooking.dto.seatPerBus(
    s.id as seat_id,
    s.seatNumber as seat_number,
    s.bus.id as bus_id,
    case when sb.id is not null then true else false end as is_booked)
    from Seat s
    left join s.seatBookings sb
         on sb.trip.id = :tripId
    where s.bus.id = :busId
    """)
    List<seatPerBus> getSeatsByTripAndBus(String busId,String tripId);
}
