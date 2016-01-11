package jp.blackawa.model;

import com.iciql.Iciql.IQColumn;
import com.iciql.Iciql.IQTable;

import java.util.UUID;

@IQTable(name = "users")
public class User {

    public User () {
        super();
    }

    public User (String name, String email, String password) {
        super();
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public boolean isValid() {
        return (!name.isEmpty()) && (!email.isEmpty()) && (!password.isEmpty());
    }

    @IQColumn(name = "id", length = 40, primaryKey = true)
    public UUID id;

    @IQColumn(name = "name", length = 60, trim = true)
    public String name;

    @IQColumn(name = "email", length = 60, trim = true)
    public String email;

    @IQColumn(name = "password", length = 100, trim = true)
    public String password;
}
