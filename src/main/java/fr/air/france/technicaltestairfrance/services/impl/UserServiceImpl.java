package fr.air.france.technicaltestairfrance.services.impl;

import fr.air.france.technicaltestairfrance.dtos.UserDto;
import fr.air.france.technicaltestairfrance.entities.UserEntity;
import fr.air.france.technicaltestairfrance.enums.ErrorMessages;
import fr.air.france.technicaltestairfrance.exceptions.UserException;
import fr.air.france.technicaltestairfrance.mappers.UserMapper;
import fr.air.france.technicaltestairfrance.repositories.UserRepository;
import fr.air.france.technicaltestairfrance.services.IUserService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;

/**
 * This is a sample Spring service class.
 *
 * <p>This class provides business logic for a user feature .
 * It interacts with the data access layer and performs various operations.
 *
 * @author MOUJAHID Mohamed
 * @version 1.0
 */
@Service
@Transactional
public class UserServiceImpl implements IUserService {

    @Autowired
    UserMapper userMapper;

    @Autowired
    UserRepository userRepository;

    /**
     * @param userDto
     * @return userDto
     *
     * Method is for registering a new USER
     */
    @Override
    public UserDto registerUser(UserDto userDto) throws UserException {
        // validate business rules
        LocalDate birthday =  LocalDate.parse(userDto.getBirthDay().toInstant().atZone(ZoneId.systemDefault()).toLocalDate().toString());
        if(Period.between(birthday, LocalDate.now()).getYears()<18)
            throw new UserException("Only adult users are allowed to register");

        if(!userDto.getCountryOfResidence().equalsIgnoreCase("france"))
            throw new UserException("Only users from France are allowed to register");

        // check if username exists
        UserEntity isUserExist = userRepository.findByUserName(userDto.getUserName());
        if(isUserExist != null)
            throw new UserException(ErrorMessages.RECORD_ALREADY_EXISTS.getErrorMessage());

        // MAPPING RECEIVED USER
        UserEntity userEntity = userMapper.userDtoToUserEntity(userDto);

        // PERSIST DATA IN DATABASE
        UserEntity newUserEntity = userRepository.save(userEntity);

        // MAPPING THE NEW PERSISTED USER
        return userMapper.userEntityToUserDto(newUserEntity);
    }

    /**
     * @param userName
     * @return userDto
     *
     * Method is for showing USER details by username
     */
    @Override
    public UserDto displayUser(String userName) throws UserException {
        UserEntity userEntity = userRepository.findByUserName(userName);
        if(userEntity == null)
            throw new UserException(ErrorMessages.NO_RECORD_FOUND.getErrorMessage());

        return userMapper.userEntityToUserDto(userEntity);
    }
}
