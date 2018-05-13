package pl.glegdev.wallet.service;

import pl.glegdev.wallet.model.User;

import java.util.List;

public interface UserService {

    User get(Long id);
    List<User> getAll();
    void save(User user);
    void save(List<User> users);
    void delete(Long id);
    User findByUsername(String username);
    User findByEmail(String email);
}
