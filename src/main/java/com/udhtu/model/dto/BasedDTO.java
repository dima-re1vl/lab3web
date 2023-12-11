package com.udhtu.model.dto;

import java.util.Objects;

public class BasedDTO<ID> {
    private ID id;

    public ID getId() {
        return id;
    }

    public void setId(ID id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BasedDTO<?> basedDTO = (BasedDTO<?>) o;
        return Objects.equals(id, basedDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
