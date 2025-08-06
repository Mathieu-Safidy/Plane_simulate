package mg.working.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "users")
public class User {
    @Id
    @Column(name = "id_users", nullable = false, length = 50)
    private String idUsers;

    @Column(name = "nom", length = 100)
    private String nom;

    @Column(name = "date_naissance", nullable = false)
    private LocalDate dateNaissance;

    @Column(name = "email", length = 100)
    private String email;

    @Column(name = "password", length = 50)
    private String password;

    @Column(name = "adresse", length = 50)
    private String adresse;

    @Column(name = "cin", length = 50)
    private String cin;

}