package com.busbooking.busbooking.repository;

import com.busbooking.busbooking.entity.Seat;
import com.busbooking.busbooking.entity.SeatBooking;
import org.hibernate.boot.jaxb.mapping.spi.JaxbPersistentAttribute;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface SeatBookingRepository extends JpaRepository<SeatBooking, String> {

    @Query("select s from SeatBooking s where s.seat.id = :seat_id and s.trip.id = :trip_id")
    SeatBooking findBySeatAndTrip(@Param("seat_id") String seat_id,
                                  @Param("trip_id") String trip_id);
    @Query("select s from SeatBooking s where s.seat.id = :seatId and s.trip.id = :tripId and s.user.id= :userId")
    SeatBooking findBySeat_IdAndTrip_IdAndUser_UserID(
            String seatId,
            String tripId,
            String userId
    );
}
