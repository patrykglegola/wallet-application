package pl.glegdev.wallet.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.glegdev.wallet.model.User;
import pl.glegdev.wallet.persistence.UserRepository;
import pl.glegdev.wallet.service.UserService;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
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
    public void save(User user) {
        userRepository.save(user);
    }

    @Override
    public void save(List<User> users) {
        userRepository.saveAll(users);
    }

    @Override
    public void delete(Long id) {
        userRepository.deleteById(id);
    }

}
