package mg.working.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "parametre")
public class Parametre {
    @Id
    @Column(name = "id_parametre", nullable = false, length = 50)
    private String idParametre;

    @Column(name = "valeur", precision = 7, scale = 2)
    private BigDecimal valeur;

    @Column(name = "date_ajout")
    private LocalDate dateAjout;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_type")
    private TypeParam idType;

}