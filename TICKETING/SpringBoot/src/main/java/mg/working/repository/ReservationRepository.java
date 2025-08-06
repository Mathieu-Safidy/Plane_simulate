package mg.working.repository;

import mg.working.model.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation,String> {
    List<Reservation> findByIdUsers_IdUsers(String idUsers);
}
