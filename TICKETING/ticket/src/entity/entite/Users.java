package entity.entite;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.springcopy.core.Session;

import utility.Entity;
import utility.annotation.Column;
import utility.annotation.Ignore;
import utility.annotation.Table;

@Table(name = "users")
public class Users extends Entity {

    @Column(name = "id_users")
    String idUsers;

    @Column(name = "nom")
    String nom;

    @Column(name = "date_naissance")
    Date dateNaissance;

    @Column(name = "email")
    String email;

    @Column(name = "password")
    String password;

    @Column(name = "adresse")
    String adresse;

    @Column(name = "CIN")
    String cin;

    @Column(name = "id_role")
    String idRole;

    @Ignore
    Role role;

    public Users() throws Exception {
        initiate();
    }

    public Users(String nom, Date dateNaissance, String email, String password, String adresse, String cin,
            String idRole) throws Exception {
        this.nom = nom;
        this.dateNaissance = dateNaissance;
        this.email = email;
        this.password = password;
        this.adresse = adresse;
        this.cin = cin;
        this.idRole = idRole;
        initiate();
    }

    public Users(String idUsers, String nom, Date dateNaissance, String email, String password, String adresse,
            String cin, String idRole) throws Exception {
        this.idUsers = idUsers;
        this.nom = nom;
        this.dateNaissance = dateNaissance;
        this.email = email;
        this.password = password;
        this.adresse = adresse;
        this.cin = cin;
        this.idRole = idRole;
        initiate();
    }

    public String getIdUsers() {
        return idUsers;
    }

    public void setIdUsers(String idUsers) {
        this.idUsers = idUsers;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Date getDateNaissance() {
        return dateNaissance;
    }

    public void setDateNaissance(Date dateNaissance) {
        this.dateNaissance = dateNaissance;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getCin() {
        return cin;
    }

    public void setCin(String cin) {
        this.cin = cin;
    }

    public void setRoleBase() {
        try {
            Role role = new Role();
            role.setIdRole(this.getIdRole());
            role = (Role)role.find(null, null)[0];
            this.setRole(role);
        } catch (Exception e) {
            e.printStackTrace();
        } 
    }

    public void manyToOne() {
        if (this.idRole != null) {
            setRoleBase();
        }
    }

    public List<Users> convertToUsers(Object[] obj) {
        List<Users> prods = new ArrayList<>();
        for (Object prod : obj) {
            Users product = (Users) prod;
            product.manyToOne();
            prods.add(product);
        }
        return prods;
    }

    public Users convertUsers(Object obj){
        Users use = (Users) obj;
        use.manyToOne();
        return use;
    }

    public String getIdRole() {
        return idRole;
    }

    public void setIdRole(String idRole) {
        this.idRole = idRole;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public boolean testLog(Session session) throws Exception {
        Users use = new Users();
        use.setEmail(this.getEmail());
        use.setPassword(this.getPassword());
        use = use.convertUsers(use.find(null, null)[0]);
        if (use.getIdUsers() != null) {
            session.setSession("auth", use.getRole().getLabel());
            session.setSession("userId", use.getIdUsers());
            return true;
        }
        return false;
    }

}
