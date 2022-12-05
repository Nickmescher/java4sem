package service;

import dao.FriendsDao;
import dao.CatsDao;
import dao.OwnerDao;
import daoReal.FriendsDaoReal;
import daoReal.CatsDaoReal;
import daoReal.OwnerDaoReal;
import model.Friends;
import model.Cats;
import model.Owner;

import java.util.ArrayList;
import java.util.List;

public class Service {
    private final OwnerDao ownersDao = new OwnerDaoReal();
    private final CatsDao catsDao = new CatsDaoReal();
    private final FriendsDao friendsDao = new FriendsDaoReal();

    public Service() {
    }

    public Friends addFriend(Cats friend1, Cats friend2) {
        Friends friends = new Friends(friend1.getId(), friend2.getId());
        friendsDao.save(friends);
        return friends;
    }

    public Owner findOwner(int id) {
        return ownersDao.findById(id);
    }

    public Cats findCat(int id) {
        return catsDao.findById(id);
    }

    public Friends findFriend(int id) {
        return friendsDao.findById(id);
    }

    public List<Owner> getAllOwners() {
        return ownersDao.findAll();
    }

    public List<Cats> getAllCats() {
        return catsDao.findAll();
    }

    public List<Friends> getAllFriends() {
        return friendsDao.findAll();
    }

    public void saveOwner(Owner owner) {
        ownersDao.save(owner);
    }

    public void saveCat(Cats cat) {
        catsDao.save(cat);
    }

    public void saveFriend(Friends friend) {
        friendsDao.save(friend);
    }

    public void deleteOwner(Owner owner) {
        ownersDao.delete(owner);
    }

    public void deleteCat(Cats cat) {
        catsDao.delete(cat);
    }

    public void deleteFriend(Friends friend) {
        friendsDao.delete(friend);
    }

    public void updateOwner(Owner owner) {
        ownersDao.update(owner);
    }

    public void updateKotik(Cats cat) {
        catsDao.update(cat);
    }

    public void updateFriend(Friends friend) {
        friendsDao.update(friend);
    }

    public List<Owner> findAllOwners() {
        return ownersDao.findAll();
    }

    public List<Cats> findAllCats() {
        return catsDao.findAll();
    }

    public List<Friends> findAllFriends() {
        return friendsDao.findAll();
    }

    public Cats findCatById(int id) {
        for (int i = 0; i < catsDao.findAll().size(); i++) {
            if (catsDao.findAll().get(i).getId() == id) {
                return catsDao.findAll().get(i);
            }
        }

        return null;
    }

    public Owner findOwnerById(int id) {
        for (int i = 0; i < ownersDao.findAll().size(); i++) {
            if (ownersDao.findAll().get(i).getId() == id) {
                return ownersDao.findAll().get(i);
            }
        }

        return null;
    }

    public List<Cats> getCatFriends(int id) {
        List<Cats> kotikFriends = new ArrayList<>();
        List<Friends> allFriends = friendsDao.findAll();

        for (Friends allFriend : allFriends) {
            if (allFriend.getCat2() == id) {
                kotikFriends.add(findCatById(allFriend.getCat1()));
            }

            if (allFriend.getCat1() == id) {
                kotikFriends.add(findCatById(allFriend.getCat2()));
            }
        }

        return kotikFriends;
    }

    public List<Cats> getOwnerCats(int id) {
        Owner owner = findOwnerById(id);
        List<Cats> kotiki = catsDao.findAll();
        List<Cats> ownerKotiki = new ArrayList<>();

        for (Cats value : kotiki) {
            if (value.getOwnerId() == owner.getId()) {
                ownerKotiki.add(value);
            }
        }

        return ownerKotiki;
    }
}