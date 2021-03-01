package ie.ucd.COMP47660GP.repositories;

import ie.ucd.COMP47660GP.entities.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Integer>{
    List<Reservation> findAll();

    @Query("select r from Reservation r where r.user.id = :user_id")
    List<Reservation> findReservations(int user_id);
}
