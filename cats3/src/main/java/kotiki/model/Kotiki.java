package kotiki.model;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "cats", schema = "public", catalog = "Cats2")
public class Kotiki {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int id;
    @Basic
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Temporal(TemporalType.DATE)
    @Column(name = "kotik_birthday")
    private Date kotikBirthday;
    @Basic
    @Column(name = "kotik_breed")
    @Enumerated(EnumType.STRING)
    private Breed kotikBreed;
    @Basic
    @Column(name = "kotik_color")
    @Enumerated(EnumType.STRING)
    private Color kotikColor;
    @Basic
    @Column(name = "kotik_name")
    private String kotikName;
    @Basic
    @Column(name = "owner_id")
    private Integer ownerId;

    public Kotiki() {
    }

    public Kotiki(String name, Date birthday, Breed breed, Color color, Integer ownerId) {
        this.kotikName = name;
        this.kotikBirthday = birthday;
        this.kotikBreed = breed;
        this.kotikColor = color;
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

    public Date getKotikBirthday() {
        return kotikBirthday;
    }

    public void setKotikBirthday(Date kotikBirthday) {
        this.kotikBirthday = kotikBirthday;
    }

    public Breed getKotikBreed() {
        return kotikBreed;
    }

    public void setKotikBreed(Breed kotikBreed) {
        this.kotikBreed = kotikBreed;
    }

    public Color getKotikColor() {
        return kotikColor;
    }

    public void setKotikColor(Color kotikColor) {
        this.kotikColor = kotikColor;
    }

    public String getKotikName() {
        return kotikName;
    }

    public void setKotikName(String kotikName) {
        this.kotikName = kotikName;
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
        Kotiki that = (Kotiki) o;
        return id == that.id && Objects.equals(kotikBirthday, that.kotikBirthday) && Objects.equals(kotikBreed, that.kotikBreed) && Objects.equals(kotikColor, that.kotikColor) && Objects.equals(kotikName, that.kotikName) && Objects.equals(ownerId, that.ownerId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, kotikBirthday, kotikBreed, kotikColor, kotikName, ownerId);
    }
}
