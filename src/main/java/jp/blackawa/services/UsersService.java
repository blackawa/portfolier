package jp.blackawa.services;
import com.iciql.Db;
import jp.blackawa.model.User;

import java.util.List;
import java.util.UUID;

/**
 * Provide Access to USERS Table
 */
public class UsersService {

    public static Db openDatabase() {
        return Db.open("jdbc:h2:file:./target/iciql", "sa", "sa");
    }

    public static UUID insert(User user) {
        user.id = UUID.randomUUID();
        try (Db db = openDatabase()) {
            boolean result;
            result = db.insert(user);
            if (!result) throw new RuntimeException("Error occurred! Failed to insert USERS record");
        }
        return user.id;
    }

    public static User findById(UUID id) {
        try (Db db = openDatabase()) {
            User user = new User();
            return db.from(user).where(user.id).is(id).selectFirst();
        }
    }

    public static User findByEmail(String email) {
        User user = new User();
        try (Db db = openDatabase()) {
            user = db.from(user).where(user.email).is(email).selectFirst();
        }
        return user;
    }

    public static List<User> findAll() {
        try (Db db = openDatabase()) {
            return db.from(new User()).select();
        }
    }
}
