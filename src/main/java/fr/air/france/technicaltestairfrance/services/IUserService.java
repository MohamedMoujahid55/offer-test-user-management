package fr.air.france.technicaltestairfrance.services;

import fr.air.france.technicaltestairfrance.dtos.UserDto;
import fr.air.france.technicaltestairfrance.exceptions.UserException;

/**
 * Service interface that contains methods to manage a User.
 *
 * @author MOUJAHID Mohamed
 * @version 1.0
 */

public interface IUserService {
    /**
     * Method for register new user
     * @param userDTO
     * @return userDTO
     */
    UserDto registerUser(UserDto userDto) throws UserException;

    /**
     * Method is used to retrieve user details by username
     * @param userName
     * @return UserDTO
     */
    UserDto displayUser(String userName) throws UserException;
}
