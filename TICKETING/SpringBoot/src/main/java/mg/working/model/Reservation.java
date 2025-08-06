package mg.working.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
@Entity
@Table(name = "reservation")
public class Reservation {
    @Id
    @Column(name = "id_reservation", nullable = false, length = 50)
    private String idReservation;

    @Column(name = "date_reservation")
    private Instant dateReservation;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_reservation_mere", nullable = false)
    private ReservationMere idReservationMere;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_type", nullable = false)
    private TypeSiege idType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_users")
    private User idUsers;

}