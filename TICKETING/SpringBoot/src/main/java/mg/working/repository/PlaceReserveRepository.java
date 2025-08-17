package mg.working.repository;

import mg.working.model.PlaceReserve;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PlaceReserveRepository extends JpaRepository<PlaceReserve,String> {
    List<PlaceReserve> getPLacereserveByIdPlace(String idPlace);
    List<PlaceReserve> getPLaceReserveByIdReservation_IdReservation(String idReservation);
}
