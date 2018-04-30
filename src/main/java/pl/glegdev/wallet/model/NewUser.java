package pl.glegdev.wallet.model;

public class NewUser extends User {
    public NewUser(String username, String password, String email, String firstName, String lastName) {
        setUsername(username);
        setPassword(password);
        setEmail(email);
        setFirstName(firstName);
        setLastName(lastName);
    }

    public User toUser() {
        User user = new User();
        user.setUsername(getUsername());
        user.setPassword(getPassword());
        user.setEmail(getEmail());
        user.setFirstName(getFirstName());
        user.setLastName(getLastName());
        return user;
    }
}
