package ie.ucd.COMP47660GP.repositories;

import ie.ucd.COMP47660GP.entities.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Integer>{
    List<Reservation> findAll();

    void createReservation(String name, String surname, String address, String phone, String email);

    List<Reservation> findReservations(String user_id);
}
