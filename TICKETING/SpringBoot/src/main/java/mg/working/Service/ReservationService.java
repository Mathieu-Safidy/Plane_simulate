package mg.working.Service;

import mg.working.Dto.ReservationDto;
import mg.working.model.Reservation;
import mg.working.repository.ReservationRepository;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Service
public class ReservationService {

    private final ReservationRepository reservationRepository;

    public ReservationService(ReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
    }

    public List<ReservationDto> coutReservationByType(String reservation) {
        return this.reservationRepository.countReservationByIdType(reservation);
    }

    public Optional<Reservation> getByIdService(String id) {
        return this.reservationRepository.findById(id);
    }

    public List<Reservation> listReservations(String id) {
        return reservationRepository.findByIdUsers_IdUsers(id);
    }
}
