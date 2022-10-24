package cats2.model.dto;

import java.sql.Date;
import java.util.Objects;

public final class OwnerDto {
    private int id;
    private final Date ownerBirthday;
    private final String ownerName;

    public OwnerDto(int id, Date ownerBirthday, String ownerName) {
        this.id = id;
        this.ownerBirthday = ownerBirthday;
        this.ownerName = ownerName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getOwnerBirthday() {
        return ownerBirthday;
    }

    public String getOwnerName() {
        return ownerName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OwnerDto ownerDto = (OwnerDto) o;
        return id == ownerDto.id && Objects.equals(ownerBirthday, ownerDto.ownerBirthday) && Objects.equals(ownerName, ownerDto.ownerName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, ownerBirthday, ownerName);
    }
}
