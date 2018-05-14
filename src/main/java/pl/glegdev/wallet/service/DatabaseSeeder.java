package pl.glegdev.wallet.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import pl.glegdev.wallet.model.User;

import java.util.ArrayList;
import java.util.List;

@Component
public class DatabaseSeeder implements CommandLineRunner {

    private UserService userService;
    private final Logger logger = LoggerFactory.getLogger(DatabaseSeeder.class);

    @Autowired
    public DatabaseSeeder(UserService userService) {
        this.userService = userService;
    }


    @Override
    public void run(String... strings) throws Exception {
        List<User> users = new ArrayList<>();

        users.add(new User(
                "adam",
                "adam123",
                "adam@gmail.com"
        ));

        users.add(new User(
                "piotrek",
                "piotr321",
                "piotrek@yahoo.com"
        ));

        userService.create(users);
        System.out.println("Lista userów z bazy: \n" + userService.getAll().toString());
    }

}
