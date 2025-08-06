package mg.working.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import java.math.BigDecimal;
import java.time.Instant;

@Getter
@Setter
@Entity
@Table(name = "vols")
public class Vol {
    @Id
    @Column(name = "id_vols", nullable = false, length = 50)
    private String idVols;

    @Column(name = "date_vol", nullable = false)
    private Instant dateVol;

    @ColumnDefault("0.0")
    @Column(name = "time_cancel", precision = 5, scale = 1)
    private BigDecimal timeCancel;

}