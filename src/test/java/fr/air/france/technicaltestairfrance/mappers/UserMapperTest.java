package fr.air.france.technicaltestairfrance.mappers;

import fr.air.france.technicaltestairfrance.dtos.UserDto;
import fr.air.france.technicaltestairfrance.entities.UserEntity;
import fr.air.france.technicaltestairfrance.enums.UserGender;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

/**
 * CLASS TEST : FOR TESTING USER MAPPER
 *
 * @author MOUJAHID Mohamed
 * @version 1.0
 */
@ExtendWith(MockitoExtension.class)
public class UserMapperTest {
    @InjectMocks
    private UserMapper userMapper;
    private UserDto userDTO;

    @Mock
    private ModelMapper modelMapper = new ModelMapper() ;

    private UserEntity user;

    /**
     * INITIALISE ALL NEEDED OBJECTS BEFORE EACH TEST METHOD CALL
     */
    @BeforeEach
    public void setUp() throws ParseException {
        // initialize users for test
        String username = "MOUJAHID";
        Date birthday =new SimpleDateFormat("dd/MM/yyyy").parse("31/12/1998");
        String country = "France";
        String phone = "056662224";
        UserGender gender = UserGender.MALE;
        userDTO = new UserDto();
        userDTO.setUserName(username);
        userDTO.setBirthDay(birthday);
        userDTO.setCountryOfResidence(country);
        userDTO.setPhoneNumber(phone);
        userDTO.setGender(gender);

        user = UserEntity.builder()
                .id(UUID.randomUUID().toString())
                .userName(username)
                .birthDay(birthday)
                .countryOfResidence(country)
                .phoneNumber(phone)
                .gender(gender)
                .build();
    }

    /**
     * INITIALISE WITH NULL AFTER EACH TEST METHOD CALL
     */
    @AfterEach
    public void clear() {
        userDTO = null;
        user = null;
    }

    /**
     * THIS Method test the MAPPER 'UserEntity' => 'UserDto'
     */
    @Test
    void shouldMapUserEntityToUserDTO() {
        // Mock Model Mapper
        when(modelMapper.map(user, UserDto.class)).thenReturn(userDTO);

        // Method to test
        UserDto userMapped = userMapper.userEntityToUserDto(user);

        // assert
        assertNotNull(userMapped);
        assertEquals(userMapped.getUserName(), userDTO.getUserName());
        assertEquals(userMapped.getBirthDay(), userDTO.getBirthDay());
        assertEquals(userMapped.getCountryOfResidence(), userDTO.getCountryOfResidence());
        assertEquals(userMapped.getGender(), userDTO.getGender());
        assertEquals(userMapped.getPhoneNumber(), userDTO.getPhoneNumber());
    }

    /**
     * THIS Method test the MAPPER 'UserDto' => 'UserEntity'
     */
    @Test
    void shouldMapUserDtoToUserEntity() {
        // Mock Model Mapper
        when(modelMapper.map(userDTO, UserEntity.class)).thenReturn(user);

        // Method to test
        UserEntity userMapped = userMapper.userDtoToUserEntity(userDTO);

        // assert
        assertNotNull(userMapped);
        assertEquals(userMapped.getUserName(), userDTO.getUserName());
        assertEquals(userMapped.getBirthDay(), userDTO.getBirthDay());
        assertEquals(userMapped.getCountryOfResidence(), userDTO.getCountryOfResidence());
        assertEquals(userMapped.getGender(), userDTO.getGender());
        assertEquals(userMapped.getPhoneNumber(), userDTO.getPhoneNumber());
    }
}
