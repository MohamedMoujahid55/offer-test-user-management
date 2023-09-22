package fr.air.france.technicaltestairfrance.repositories;

import fr.air.france.technicaltestairfrance.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * This is a sample Spring interface.
 *
 * <p>This interface extends from JpaRepository where we could have the ability to explore all persistence methods.
 * JpaRepository is for Sql databases
 *
 * @author MOUJAHID Mohamed
 * @version 1.0
 */
public interface UserRepository extends JpaRepository<UserEntity, String> {
    /**
     * This method retrieves a user by username.
     *
     * @param username .
     * @return The userEntity object if found, or null if not found.
     */
    UserEntity findByUserName(String username);

}
