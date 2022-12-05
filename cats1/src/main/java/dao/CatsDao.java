package dao;

import model.Cats;

import java.util.List;

public interface CatsDao {
    Cats findById(int id);

    void save(Cats cats);

    void update(Cats cats);

    void delete(Cats cats);

    Cats findOwnerById(int id);

    List<Cats> findAll();
}