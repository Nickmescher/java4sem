package cats2.model.dto;

import cats2.model.entity.*;

import java.sql.Date;
import java.util.Objects;

public final class CatDto {

    private int id;
    private Date catBirthday;
    private Breed catBreed;
    private Color catColor;
    private String catName;
    private int ownerId;

    public CatDto(int id, Date catBirthday, Breed catBreed, Color catColor, String catName, int ownerId) {
        this.id = id;
        this.catBirthday = catBirthday;
        this.catBreed = catBreed;
        this.catColor = catColor;
        this.catName = catName;
        this.ownerId = ownerId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getCatBirthday() {
        return catBirthday;
    }

    public void setCatBirthday(Date kotikBirthday) {
        this.catBirthday = kotikBirthday;
    }

    public Breed getCatBreed() {
        return catBreed;
    }

    public void setCatBreed(Breed catBreed) {
        this.catBreed = catBreed;
    }

    public String getCatName() {
        return catName;
    }

    public void setCatName(String catName) {
        this.catName = catName;
    }

    public Color getCatColor() {
        return catColor;
    }

    public void setKotikColor(Color kotikColor) {
        this.catColor = kotikColor;
    }

    public int getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(int ownerId) {
        this.ownerId = ownerId;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        var that = (CatDto) obj;
        return Objects.equals(this.id, that.id) &&
                Objects.equals(this.catBirthday, that.catBirthday) &&
                Objects.equals(this.catBreed, that.catBreed) &&
                Objects.equals(this.catColor, that.catColor) &&
                Objects.equals(this.catName, that.catName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, catBirthday, catBreed, catColor, catName);
    }

    @Override
    public String toString() {
        return "CatDto[" +
                "id=" + id + ", " +
                "catBirthday=" + catBirthday + ", " +
                "catBreed=" + catBreed + ", " +
                "catColor=" + catColor + ", " +
                "catName=" + catName + ']';
    }

}
