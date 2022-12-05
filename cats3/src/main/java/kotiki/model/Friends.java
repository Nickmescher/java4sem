package kotiki.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "friends", schema = "public", catalog = "postgres")
public class Friends {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int id;

    @Basic
    @Column(name = "kotik_1")
    private Integer kotik1;

    @Basic
    @Column(name = "kotik_2")
    private Integer kotik2;

    public Friends() {
    }

    public Friends(int first, int second) {
        this.kotik1 = first;
        this.kotik2 = second;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Integer getKotik1() {
        return kotik1;
    }

    public void setKotik1(Integer kotik1) {
        this.kotik1 = kotik1;
    }

    public Integer getKotik2() {
        return kotik2;
    }

    public void setKotik2(Integer kotik2) {
        this.kotik2 = kotik2;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Friends that = (Friends) o;
        return id == that.id && Objects.equals(kotik1, that.kotik1) && Objects.equals(kotik2, that.kotik2);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, kotik1, kotik2);
    }
}
