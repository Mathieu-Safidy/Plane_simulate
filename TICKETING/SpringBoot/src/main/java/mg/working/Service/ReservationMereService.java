package mg.working.Service;

import mg.working.model.ReservationMere;
import mg.working.repository.ReservationMereRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReservationMereService {


    private final ReservationMereRepository reservationMereRepository;

    public ReservationMereService(ReservationMereRepository reservationMereRepository) {
        this.reservationMereRepository = reservationMereRepository;
    }

    public List<ReservationMere> findByIdUser(String id) {
        return reservationMereRepository.findByIdUsers_IdUsers(id);
    }
    public Optional<ReservationMere> findBydId(String id) {
        return reservationMereRepository.findById(id);
    }
}
