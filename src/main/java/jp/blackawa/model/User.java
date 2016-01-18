package jp.blackawa.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
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
    private UUID id;

    private String name;
    private String email;
    private String password;

    public boolean isValid() {
        return (!name.isEmpty()) && (!email.isEmpty()) && (!password.isEmpty());
    }
}
