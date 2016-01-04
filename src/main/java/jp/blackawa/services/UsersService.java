package jp.blackawa.services;

import com.iciql.Db;
import jp.blackawa.model.User;

/**
 * Provide Access to USERS Table
 */
public class UsersService {

    /**
     * Insert User data to Database
     * @param user user information
     * @return result
     */
    public static boolean insert(User user) {
        boolean result;
        try (Db db = Db.open("jdbc:h2:file:./target/iciql", "sa", "sa")) {
            result = db.insert(user);
        }
        return result;
    }

    public static User findByEmail(String email) {
        User user = new User();
        try (Db db = Db.open("jdbc:h2:file:./target/iciql", "sa", "sa")) {
            user = db.from(user).where(user.email).is(email).selectFirst();
        }
        return user;
    }
}
