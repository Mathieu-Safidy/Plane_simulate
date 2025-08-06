package mg.working.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "type_param")
public class TypeParam {
    @Id
    @Column(name = "id_type", nullable = false, length = 50)
    private String idType;

    @Column(name = "libele", length = 50)
    private String libele;

}