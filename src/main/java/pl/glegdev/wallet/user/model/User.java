package pl.glegdev.wallet.user.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import org.springframework.context.annotation.Role;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

@Getter
@Setter
@ToString
@NoArgsConstructor
@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
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

    @Column
    private UserRole role;

    public User(@NotEmpty String username, @NotEmpty String password, @Email @NotEmpty String email) {
        this.username = username;
        this.password = password;
        this.email = email;
    }
}
