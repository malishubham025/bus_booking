package com.busbooking.busbooking.repository;

import com.busbooking.busbooking.entity.Trip;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface TripRepository extends JpaRepository<Trip,String> {

    Trip findByBus_IdAndDepartureTime(String busId, LocalDate time);

    List<Trip> findByBus_Id(String bus_id);


    @Query("select t.id, count('abc') from Trip t join t.seatBookings  sb group by t.id ")
    List<Object> getCountPerTrip();
}
