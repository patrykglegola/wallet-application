package pl.glegdev.wallet.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pl.glegdev.wallet.model.User;
import pl.glegdev.wallet.persistence.UserRepository;
import pl.glegdev.wallet.service.UserService;

import javax.persistence.EntityExistsException;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User get(Long id) {
        return userRepository.getOne(id);
    }

    @Override
    public List<User> getAll() {
        return userRepository.findAll();
    }

    @Override
    public User create(User user) {
        checkNotExist(user);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    private void checkNotExist(User user) {
        if (userRepository.findByUsername(user.getUsername()) != null) {
            throw new EntityExistsException("User with username " +
                    user.getUsername() + " already exists.");
        }
        if (userRepository.findByEmail(user.getEmail()) != null) {
            throw new EntityExistsException("User with email address " +
                    user.getEmail() + " already exists.");
        }
    }

    @Override
    public List<User> create(List<User> users) {
        List<User> savedUsers = new ArrayList<>();
        for (User user : users) {
           savedUsers.add(create(user));
        }
        return savedUsers;
    }

    @Override
    public User update(User user) {
        return userRepository.save(user);
    }

    @Override
    public void delete(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

}
