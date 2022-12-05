package kotiki.service;

import kotiki.model.*;
import kotiki.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserService implements UserDetailsService {
    private final UserRepository userRepo;

    @Autowired
    public UserService(UserRepository userRepo) {
        this.userRepo = userRepo;
    }

    public List<User> getAllUsers() {
        return userRepo.findAll();
    }

    public void createUser(String username, String password, Boolean active, Set<Role> roles) {
        User user = new User(username, password, active, roles);
        userRepo.save(user);
    }

    public List<User> getUsersById(Integer id) {
        return Collections.singletonList(userRepo.findById(id).get());
    }

    public User getUserById(Integer id) {
        return userRepo.findById(id).get();
    }

    public List<Role> getUserRoles() {
        return List.of(Role.values());
    }

    public void deleteUser(Integer id) {
        userRepo.deleteById(id);
    }

    public Optional<User> findByUserName(String userName) {
        return Optional.ofNullable(userRepo.findByUsername(userName));
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return findByUserName(username).orElse(null);
    }
}