package com.busbooking.busbooking.repository;

import com.busbooking.busbooking.dto.BusesDTO;
import com.busbooking.busbooking.entity.Bus;
import org.assertj.core.api.ObjectEnumerableAssert;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BusRepository extends JpaRepository<Bus,String> {

    @Query("""
       select new com.busbooking.busbooking.dto.BusesDTO(
           b.id,
           b.bus_name,
           b.bus_number,
           b.destination,
           b.source,
           b.total_seats,
           t.departureTime,
           t.id,
           count(sb)
       )
       from Bus b
       join b.trips t
       left join t.seatBookings sb
       group by
           b.id,
           b.bus_name,
           b.bus_number,
           b.destination,
           b.source,
           b.total_seats,
           t.departureTime,
           t.id
       """)
    List<BusesDTO> getBusesByTrips();


}
