package jp.blackawa.services;

import com.iciql.Db;
import jp.blackawa.model.User;

import java.util.List;

/**
 * Provide Access to USERS Table
 */
public class UsersService {

    public static Db openDatabase() {
        return Db.open("jdbc:h2:file:./target/iciql", "sa", "sa");
    }

    public static boolean insert(User user) {
        boolean result;
        try (Db db = openDatabase()) {
            result = db.insert(user);
        }
        return result;
    }

    public static User findById(Long id) {
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
