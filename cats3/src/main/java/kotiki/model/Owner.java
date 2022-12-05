package kotiki.model;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "cat_owner", schema = "public", catalog = "Cats2")
public class Owner {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int id;
    @Basic
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Temporal(TemporalType.DATE)
    @Column(name = "owner_birthday")
    private Date ownerBirthday;
    @Basic
    @Column(name = "owner_name")
    private String ownerName;
    @Basic
    @Column(name = "userId")
    @CollectionTable(name = "users", joinColumns = @JoinColumn(name = "user_id"))
    private Integer userId;

    public Owner() {
    }

    public Owner(String name, Date birthday, Integer userId) {
        this.ownerName = name;
        this.ownerBirthday = birthday;
        this.userId = userId;
    }

    public void addKotik(Kotiki kotik) {
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

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
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
