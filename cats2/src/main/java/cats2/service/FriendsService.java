package cats2.service;

import cats2.model.dto.*;
import cats2.model.entity.*;
import cats2.repository.*;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class FriendsService {

    private final FriendsRepository friendsRepo;

    private final ModelMapper modelMapper;

    @Autowired
    public FriendsService(FriendsRepository friendsRepo, ModelMapper modelMapper) {
        this.friendsRepo = friendsRepo;
        this.modelMapper = modelMapper;
    }

    public List<Friends> getAllFriends() {
        return friendsRepo.findAll();
    }

    public void createFriends(FriendsDto friends) {
        Friends friend = modelMapper.map(friends, Friends.class);
        friendsRepo.save(friend);
    }

    public Friends getFriendsById(Integer id) {
        return friendsRepo.findById(id).get();
    }

    public void deleteFriends(Integer id) {
        friendsRepo.deleteById(id);
    }

    public List<Integer> findCatFriends(Integer id, List<Friends> friends) {
        List<Integer> friendsFound = new ArrayList();

        for (Friends friend: friends) {
            if (id == friend.getCat1()) {
                friendsFound.add(friend.getCat2());
            }
            if (id == friend.getCat2()) {
                friendsFound.add(friend.getCat1());
            }
        }

        Set<Integer> set = new HashSet<>(friendsFound);
        friendsFound.clear();
        friendsFound.addAll(set);

        return friendsFound;
    }
}