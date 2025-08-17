package mg.working.Dto;

import mg.working.model.TypeSiege;

public class ReservationDto {
    Long count;
    TypeSiege typeSiege;

    public ReservationDto(Long count,TypeSiege type) {
        this.count = count;
        this.typeSiege = type;
    }

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }

    public TypeSiege getTypeSiege() {
        return typeSiege;
    }

    public void setTypeSiege(TypeSiege typeSiege) {
        this.typeSiege = typeSiege;
    }

    
}
