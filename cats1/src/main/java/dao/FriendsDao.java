package dao;

import model.Friends;

import java.util.List;

public interface FriendsDao {
    Friends findById(int id);

    void save(Friends friend);

    void update(Friends friend);

    void delete(Friends friend);

    List<Friends> findAll();
}