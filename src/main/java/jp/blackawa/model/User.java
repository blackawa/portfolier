package jp.blackawa.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Email;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.UUID;

@Entity
@Table(name = "USER")
@Data
@NoArgsConstructor
public class User {

    public User (UUID id, String name, String email, String password) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
    }

    @Id
    @NotNull
    private UUID id;
    @Size(max = 80)
    @NotNull
    private String name;
    @Email
    @NotNull
    private String email;
    @NotNull
    private String password;

    public boolean isValid() {
        return (!name.isEmpty()) && (!email.isEmpty()) && (!password.isEmpty());
    }
}
