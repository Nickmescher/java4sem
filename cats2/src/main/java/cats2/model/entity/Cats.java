package cats2.model.entity;

import javax.persistence.*;
import java.sql.Date;
import java.util.Objects;

@Entity
@Table(name = "cats", schema = "public", catalog = "Cats2")
public class Cats {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int id;
    @Basic
    @Column(name = "cat_birthday")
    private Date catBirthday;
    @Basic
    @Column(name = "cat_breed")
    @Enumerated(EnumType.STRING)
    private Breed catBreed;
    @Basic
    @Column(name = "cat_color")
    @Enumerated(EnumType.STRING)
    private Color catColor;
    @Basic
    @Column(name = "cat_name")
    private String catName;
    @Basic
    @Column(name = "owner_id")
    private Integer ownerId;

    public Cats() {
    }

    public Cats(String name, Date birthday, Breed breed, Color color, Integer ownerId) {
        this.catName = name;
        this.catBirthday = birthday;
        this.catBreed = breed;
        this.catColor = color;
        this.ownerId = ownerId;
    }

    public void setOwner(Owner owner) {
        this.ownerId = owner.getId();
    }

    public void removeOwner() {
        this.ownerId = null;
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

    public void setCatBirthday(Date catBirthday) {
        this.catBirthday = catBirthday;
    }

    public Breed getCatBreed() {
        return catBreed;
    }

    public void setCatBreed(Breed catBreed) {
        this.catBreed = catBreed;
    }

    public Color getCatColor() {
        return catColor;
    }

    public void setCatColor(Color catColor) {
        this.catColor = catColor;
    }

    public String getCatName() {
        return catName;
    }

    public void setCatName(String catName) {
        this.catName = catName;
    }

    public Integer getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(Integer ownerId) {
        this.ownerId = ownerId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cats that = (Cats) o;
        return id == that.id && Objects.equals(catBirthday, that.catBirthday) && Objects.equals(catBreed, that.catBreed) && Objects.equals(catColor, that.catColor) && Objects.equals(catName, that.catName) && Objects.equals(ownerId, that.ownerId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, catBirthday, catBreed, catColor, catName, ownerId);
    }
}
