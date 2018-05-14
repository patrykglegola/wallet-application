package pl.glegdev.wallet.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

@Getter
@Setter
@ToString
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue
    private Long id;

    @Column(unique = true)
    @NotEmpty
    private String username;

    @NotEmpty
    private String password;

    @Column(unique = true)
    @Email
    @NotEmpty
    private String email;

    public User(@NotEmpty String username, @NotEmpty String password, @Email @NotEmpty String email) {
        this.username = username;
        this.password = password;
        this.email = email;
    }
}
