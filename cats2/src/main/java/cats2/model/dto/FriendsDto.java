package cats2.model.dto;

import java.util.Objects;

public final class FriendsDto {
    private int id;
    private Integer cat1;
    private Integer cat2;

    public FriendsDto(int id, Integer cat1, Integer cat2) {
        this.id = id;
        this.cat1 = cat1;
        this.cat2 = cat2;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Integer getCat1() {
        return cat1;
    }

    public void setCat1(Integer cat1) {
        this.cat1 = cat1;
    }

    public Integer getCat2() {
        return cat2;
    }

    public void setCat2(Integer cat2) {
        this.cat2 = cat2;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FriendsDto that = (FriendsDto) o;
        return id == that.id && Objects.equals(cat1, that.cat1) && Objects.equals(cat2, that.cat2);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, cat1, cat2);
    }
}
