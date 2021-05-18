package ie.ucd.COMP47660GP.repositories;

import ie.ucd.COMP47660GP.entities.Flight;
import ie.ucd.COMP47660GP.entities.Reservation;
import ie.ucd.COMP47660GP.entities.User;
import ie.ucd.COMP47660GP.exception.NoSuchFlightException;
import ie.ucd.COMP47660GP.exception.NoSuchReservationException;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Integer>{
    List<Reservation> findAll();

    @Query("select r from Reservation r where r.user.id = :user_id")
    List<Reservation> findUsersReservations(Long user_id);

    @Query("select r from Reservation r where r.id = :reservation_id")
    List<Reservation> findReservationWithReservationID(int reservation_id);

    @Transactional
    @Modifying
    @Query("update Reservation r set r.cancelled = :cancelled, r.flight = :flight, r.user = :user where r.id = :resID")
    void updateReservationInfo(boolean cancelled, Flight flight, User user, int resID) throws NoSuchReservationException;
}
