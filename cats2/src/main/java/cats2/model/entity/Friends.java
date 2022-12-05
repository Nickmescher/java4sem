package cats2.model.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "friends", schema = "public", catalog = "Cats2")
public class Friends {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int id;

    @Basic
    @Column(name = "cat1")
    private Integer cat1;

    @Basic
    @Column(name = "cat2")
    private Integer cat2;

    public Friends() {
    }

    public Friends(int first, int second) {
        this.cat1 = first;
        this.cat2 = second;
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
        Friends that = (Friends) o;
        return id == that.id && Objects.equals(cat1, that.cat1) && Objects.equals(cat2, that.cat2);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, cat1, cat2);
    }
}
