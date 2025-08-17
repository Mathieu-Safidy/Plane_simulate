package mg.working.repository;

import mg.working.model.ReservationMere;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReservationMereRepository extends JpaRepository<ReservationMere,String> {
    public List<ReservationMere> findByIdUsers_IdUsers(String idUsers);
}
 