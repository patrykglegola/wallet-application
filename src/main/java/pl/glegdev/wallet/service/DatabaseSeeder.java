package pl.glegdev.wallet.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import pl.glegdev.wallet.model.NewUser;
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

        users.add(new NewUser(
                "adam",
                "adam123",
                "adam@gmail.com",
                "Adam",
                "Adamowski"
        ).toUser());

        users.add(new NewUser(
                "piotrek",
                "piotr321",
                "piotrek@yahoo.com",
                "Piotr",
                "Piotrowski"
        ).toUser());

        userService.save(users);
        System.out.println("Lista userów z bazy: \n" + userService.getAll().toString());
    }

}
