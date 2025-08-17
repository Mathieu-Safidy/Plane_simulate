package mg.working.repository;

import mg.working.Dto.ReservationDto;
import mg.working.model.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation,String> {
    List<Reservation> findByIdUsers_IdUsers(String idUsers);

    @Query("select new mg.working.Dto.ReservationDto(count(p), r.idType) " +
            "from Reservation r join PlaceReserve p on p.idReservation.idReservation = r.idReservation " +
            "where r.idReservation = :id " +
            "group by r.idType")
    List<ReservationDto> countReservationByIdType(@Param("id") String idType);

}
