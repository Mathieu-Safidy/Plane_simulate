package mg.working.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@Entity
@Table(name = "place_reserve")
public class PlaceReserve {
    @Id
    @Column(name = "id_place", nullable = false, length = 50)
    private String idPlace;

    @Column(name = "prix", nullable = false, precision = 15, scale = 2)
    private BigDecimal prix;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_reservation", nullable = false)
    private Reservation idReservation;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_type", nullable = false)
    private TypeSiege idType;

}