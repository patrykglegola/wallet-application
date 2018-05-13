package pl.glegdev.wallet.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pl.glegdev.wallet.model.User;
import pl.glegdev.wallet.service.UserService;

import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping("/users")
public class UserRestController {


    private UserService userService;

    @Autowired
    public UserRestController(UserService userService) {
        this.userService = userService;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public User create(@RequestBody @Valid User user) {
        return userService.create(user);
    }

    @RequestMapping(path = "/all", method = RequestMethod.GET)
    public List<User> getAll() {
        return userService.getAll();
    }

}
