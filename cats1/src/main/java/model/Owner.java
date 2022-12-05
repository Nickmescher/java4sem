package model;

import javax.persistence.*;
import java.sql.Date;
import java.util.Objects;

@Entity
@Table(name = "cat_owner", schema = "public", catalog = "Cats1")
public class Owner {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int id;
    @Basic
    @Column(name = "birthday")
    private Date ownerBirthday;
    @Basic
    @Column(name = "name")
    private String ownerName;

    public Owner() {
    }

    public Owner(String name, Date birthday) {
        this.ownerName = name;
        this.ownerBirthday = birthday;
    }

    public void addCat(Cats kotik) {
        kotik.setOwner(this);
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

    public void setOwnerBirthday(Date ownerBirthday) {
        this.ownerBirthday = ownerBirthday;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Owner that = (Owner) o;
        return id == that.id && Objects.equals(ownerBirthday, that.ownerBirthday) && Objects.equals(ownerName, that.ownerName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, ownerBirthday, ownerName);
    }
}