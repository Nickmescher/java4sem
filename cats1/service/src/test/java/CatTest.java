import model.*;
import org.junit.Before;
import org.junit.Test;
import service.Service;

import java.sql.Date;
import java.util.List;

import static org.junit.Assert.*;

public class CatTest {
    private Service service;

    @Before
    public void setUp() {
        service = new Service();
    }

    @Test
    public void AddOwner() {
        Owner owner = new Owner("Masha", new Date(25-04-2003));

        service.saveOwner(owner);
        assertEquals(1, service.getAllOwners().size());

        service.deleteOwner(owner);
    }

    @Test
    public void AddKotiki() {
        Cats kotik = new Cats("Kot", new Date(11-11-2011), Breed.BALD, Color.BLACK);

        service.saveCat(kotik);
        assertEquals(1, service.getAllCats().size());

        service.deleteCat(kotik);
    }

    @Test
    public void AddFriends() {
        Cats kotik = new Cats("Kot", new Date(11-11-2011), Breed.BALD, Color.BLACK);
        Cats kotik2 = new Cats("Kot2", new Date(11-11-2011), Breed.BRITTSH, Color.WHITE);

        service.saveCat(kotik);
        service.saveCat(kotik2);

        Friends friend = service.addFriend(kotik, kotik2);

        service.updateKotik(kotik);
        service.updateKotik(kotik2);

        List<Cats> kotikFriends = service.getCatFriends(kotik.getId());

        assertEquals(1, service.getAllFriends().size());

        service.deleteCat(kotik);
        service.deleteCat(kotik2);
        service.deleteFriend(friend);
    }

    @Test
    public void AddFriendsToKotikAndDelete() {
        Owner owner = new Owner("Masha",new Date(25-04-2003));
        Cats kotik = new Cats("Kot", new Date(11-11-2011), Breed.HOMELESS, Color.BLACK);
        Cats kotik2 = new Cats("Kot2", new Date(11-11-2011), Breed.BRITTSH, Color.WHITE);

        service.saveOwner(owner);
        service.saveCat(kotik);
        service.saveCat(kotik2);

        owner.addCat(kotik);
        owner.addCat(kotik2);

        service.updateOwner(owner);

        Friends friend = service.addFriend(kotik, kotik2);

        service.updateKotik(kotik);
        service.updateKotik(kotik2);

        assertEquals(2, service.getAllCats().size());
        assertEquals(1, service.getAllOwners().size());
        assertEquals(1, service.getAllFriends().size());

        owner.setOwnerName("Sasha");

        assertEquals("Sasha", owner.getOwnerName());

        service.updateOwner(owner);

        service.deleteCat(kotik);
        service.deleteCat(kotik2);
        service.deleteFriend(friend);
        service.deleteOwner(owner);

        assertEquals(0, service.getAllCats().size());
        assertEquals(0, service.getAllOwners().size());
        assertEquals(0, service.getAllFriends().size());
    }
}