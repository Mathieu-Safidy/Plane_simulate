package entity.entite;

import java.util.ArrayList;
import java.util.List;

import utility.Entity;
import utility.annotation.Column;
import utility.annotation.Table;

@Table(name = "role")
public class Role extends Entity {
    
    @Column(name = "id_role")
    String idRole;

    @Column(name = "label")
    String label;

    public Role() throws Exception{
        initiate();
    }

    public Role(String label) throws Exception{
        this.label = label;
        initiate();
    }

    public Role(String idRole, String label) throws Exception{
        this.idRole = idRole;
        this.label = label;
        initiate();
    }

    public String getIdRole() {
        return idRole;
    }

    public void setIdRole(String idRole) {
        this.idRole = idRole;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public List<Role> convertToRole(Object[] obj) {
        List<Role> prods = new ArrayList<>();
        for (Object prod : obj) {
            Role product = (Role) prod;
            prods.add(product);
        }
        return prods;
    }

    
}
