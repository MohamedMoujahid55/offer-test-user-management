package fr.air.france.technicaltestairfrance.services;

import fr.air.france.technicaltestairfrance.dtos.UserDto;
import fr.air.france.technicaltestairfrance.entities.UserEntity;
import fr.air.france.technicaltestairfrance.enums.UserGender;
import fr.air.france.technicaltestairfrance.exceptions.UserException;
import fr.air.france.technicaltestairfrance.mappers.UserMapper;
import fr.air.france.technicaltestairfrance.repositories.UserRepository;
import fr.air.france.technicaltestairfrance.services.impl.UserServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * CLASS TEST : FOR TESTING USER SERVICE MAPPER
 *
 * @author MOUJAHID Mohamed
 * @version 1.0
 */
@ExtendWith(MockitoExtension.class)
public class UserServiceTest {
    @Mock
    private UserRepository userRepository;
    @Mock
    private UserMapper userMapper = new UserMapper();
    @InjectMocks
    private IUserService userService = new UserServiceImpl();
    private UserDto userDto;
    private UserEntity userEntity;
    private final String USERNAME = "MOUJAHID";
    private final Date BIRTHDAY=new SimpleDateFormat("dd/MM/yyyy").parse("31/12/1998");
    private final String COUNTRY_OF_RESIDENCE = "France";
    private final String PHONE = "+3361234565";
    private final UserGender GENDER = UserGender.MALE;

    public UserServiceTest() throws ParseException {
    }

    /**
     * INITIALISE ALL NEEDED OBJECTS BEFORE EACH TEST METHOD CALL
     */
    @BeforeEach
    public void setUp() {
        userDto = UserDto.builder()
                .userName(USERNAME)
                .birthDay(BIRTHDAY)
                .countryOfResidence(COUNTRY_OF_RESIDENCE)
                .phoneNumber(PHONE)
                .gender(GENDER)
                .build();

        userEntity = UserEntity.builder()
                .userName(USERNAME)
                .birthDay(BIRTHDAY)
                .countryOfResidence(COUNTRY_OF_RESIDENCE)
                .phoneNumber(PHONE)
                .gender(GENDER)
                .build();

        // MOCKS
        Mockito.when(userMapper.userEntityToUserDto(userEntity)).thenReturn(userDto);
    }

    /**
     * TEST METHOD : SHOULD REGISTER A USER
     */
    @Test
    public void shouldServiceRegisterUser() throws UserException {
        //MOCK
        Mockito.when(userRepository.findByUserName(userDto.getUserName())).thenReturn(null);
        Mockito.when(userRepository.save(userEntity)).thenReturn(userEntity);
        Mockito.when(userMapper.userDtoToUserEntity(userDto)).thenReturn(userEntity);

        //METHODE TO TEST
        UserDto savedUser = userService.registerUser(userDto);

        // ASSERT
        assertNotNull(savedUser);
        assertEquals(savedUser.getUserName(), USERNAME);
        assertEquals(savedUser.getBirthDay(), BIRTHDAY);
        assertEquals(savedUser.getCountryOfResidence(), COUNTRY_OF_RESIDENCE);
        assertEquals(savedUser.getGender(), GENDER);
        assertEquals(savedUser.getPhoneNumber(), PHONE);
    }

    /**
     * TEST METHOD : SHOULD RETRIEVE A USER
     */
    @Test
    public void shouldServiceShowUserUser() throws UserException {
        //MOCK
        Mockito.when(userRepository.findByUserName(userDto.getUserName())).thenReturn(userEntity);

        //METHODE TO TEST
        UserDto savedUser = userService.displayUser(userDto.getUserName());

        //ASSERT
        assertNotNull(savedUser);
        assertEquals(savedUser.getUserName(), USERNAME);
        assertEquals(savedUser.getBirthDay(), BIRTHDAY);
        assertEquals(savedUser.getCountryOfResidence(), COUNTRY_OF_RESIDENCE);
        assertEquals(savedUser.getGender(), GENDER);
        assertEquals(savedUser.getPhoneNumber(), PHONE);
    }

}
