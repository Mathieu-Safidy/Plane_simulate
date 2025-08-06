package mg.working.Service;

import mg.working.model.Reservation;
import mg.working.repository.ReservationRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReservationService {

    private final ReservationRepository reservationRepository;

    public ReservationService(ReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
    }

    public List<Reservation> listReservations(String id) {
        return reservationRepository.findByIdUsers_IdUsers(id);
    }
}
