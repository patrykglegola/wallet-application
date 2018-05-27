package pl.glegdev.wallet.user.service;

import pl.glegdev.wallet.user.model.User;

import java.util.List;

public interface UserService {

    User get(Long id);
    List<User> getAll();
    User create(User user);
    List<User> create(List<User> users);
    User update(User user);
    void delete(Long id);
    User findByUsername(String username);
    User findByEmail(String email);
}
