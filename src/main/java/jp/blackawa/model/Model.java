package jp.blackawa.model;

import java.util.List;
import java.util.UUID;

/**
 * Define sets of CRUD methods which this application requires.
 */
public interface Model {
    // Users
    UUID createUser(User user);
    User readUser(UUID id);
    List<User> readAllUsers();
    void updateUser(User user);
    User deleteUser(UUID id);

    // Stems
    UUID createStem(Stem user);
    Stem readStem(UUID id);
    List<Stem> readAllStems();
    void updateStem(Stem user);
    Stem deleteStem(UUID id);

}
