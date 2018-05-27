package pl.glegdev.wallet.security.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import pl.glegdev.wallet.security.model.LoggedUser;
import pl.glegdev.wallet.user.persistence.UserRepository;

import java.util.Optional;

@Service
@Primary
public class UserDetailsServiceImpl implements UserDetailsService{

    private UserRepository userRepository;

    @Autowired
    public UserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return Optional.ofNullable(userRepository.findByUsername(username)).map(user -> new LoggedUser(
                user.getUsername(), user.getPassword()))
                .orElseThrow(() -> new UsernameNotFoundException(username));
    }
}
