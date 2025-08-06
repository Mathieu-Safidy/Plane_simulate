package param;

import java.util.ArrayList;
import java.util.List;

import utility.Entity;
import utility.annotation.Column;
import utility.annotation.Table;

@Table(name = "type_param")
public class TypeParam extends Entity{
    
    @Column(name = "id_type")
    String idType;

    @Column(name = "libele")
    String libele;

    public TypeParam() throws Exception {
        initiate();
    }

    public TypeParam(String libele) throws Exception {
        this.libele = libele;
        initiate();
    }

    public TypeParam(String idType, String libele) throws Exception {
        this.idType = idType;
        this.libele = libele;
        initiate();
    }

    public String getIdType() {
        return idType;
    }

    public void setIdType(String idType) {
        this.idType = idType;
    }

    public String getLibele() {
        return libele;
    }

    public void setLibele(String libele) {
        this.libele = libele;
    }

    public List<TypeParam> convertToTypeParam(Object[] obj) {
        List<TypeParam> prods = new ArrayList<>();
        for (Object prod : obj) {
            TypeParam product = (TypeParam) prod;
            prods.add(product);
        }
        return prods;
    }
    
}
