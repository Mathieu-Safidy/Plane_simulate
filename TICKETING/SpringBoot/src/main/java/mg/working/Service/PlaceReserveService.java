package mg.working.Service;

import mg.working.model.PlaceReserve;
import mg.working.repository.PlaceReserveRepository;
import mg.working.repository.ReservationMereRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlaceReserveService {

    private final PlaceReserveRepository placeReserveRepository;

    public PlaceReserveService(PlaceReserveRepository placeReserveRepository) {
        this.placeReserveRepository = placeReserveRepository;
    }

    public List<PlaceReserve> getAllPlaceReserveByReservation(String idReservation) {
        return this.placeReserveRepository.getPLaceReserveByIdReservation_IdReservation(idReservation);
    }

}
