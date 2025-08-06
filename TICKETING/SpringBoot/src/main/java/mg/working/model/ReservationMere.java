package mg.working.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "reservation_mere")
public class ReservationMere {
    @Id
    @Column(name = "id_reservation_mere", nullable = false, length = 50)
    private String idReservationMere;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_vols", nullable = false)
    private Vol idVols;

    @OneToMany(mappedBy = "idReservationMere" , cascade = CascadeType.ALL , fetch = FetchType.EAGER)
    private List<Reservation> reservations;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_users")
    private User idUsers;
}