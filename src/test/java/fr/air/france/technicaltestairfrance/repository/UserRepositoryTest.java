package fr.air.france.technicaltestairfrance.repository;

import fr.air.france.technicaltestairfrance.entities.UserEntity;
import fr.air.france.technicaltestairfrance.enums.UserGender;
import fr.air.france.technicaltestairfrance.repositories.UserRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * CLASS TEST : FOR TESTING USER REPOSITORY
 *
 * @author MOUJAHID Mohamed
 * @version 1.0
 */
@ExtendWith(SpringExtension.class)
@DataJpaTest
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    private UserEntity user ;

    private final String USERNAME = "MOUJAHID";
    private final Date BIRTHDAY=new SimpleDateFormat("dd/MM/yyyy").parse("31/12/1998");
    private final String COUNTRY_OF_RESIDENCE = "France";
    private final String PHONE = "+3361234565";
    private final UserGender GENDER = UserGender.MALE;

    public UserRepositoryTest() throws ParseException {
    }

    /**
     * DATA INITIALIZATION BEFORE METHOD CALL
     */
    @BeforeEach
    public void setUp() throws ParseException {
        user = UserEntity.builder()
                .userName(USERNAME)
                .birthDay(BIRTHDAY)
                .countryOfResidence(COUNTRY_OF_RESIDENCE)
                .phoneNumber(PHONE)
                .gender(GENDER)
                .build();
        userRepository.save(user);

    }

    /**
     * CLEAR DATA AFTER EACH METHOD TEST
     */
    @AfterEach
    public void clear() {
        user = null;
        userRepository.deleteAll();
    }

    /**
     * THIS TEST METHOD IS FOR TESTING IS AN USER SAVED
     */
    @Test
    public void shouldSaveUser() throws ParseException {
        // DATA INITIALISATION
        String username = "Lucie";
        Date birthday=new SimpleDateFormat("dd/MM/yyyy").parse("31/12/1999");
        String country = "France";
        String phone = "0590253364";
        UserGender gender = UserGender.MALE;

        UserEntity user = UserEntity.builder()
                .userName(username)
                .birthDay(birthday)
                .phoneNumber(phone)
                .countryOfResidence(country)
                .gender(gender)
                .build();

        // METHOD TO TEST
        UserEntity savedUser = userRepository.save(user);

        // ASSERT TEST
        assertNotNull(savedUser);
        assertThat(savedUser.getId()).isNotNull();
        assertEquals(savedUser.getUserName(), username);
        assertEquals(savedUser.getBirthDay(), birthday);
        assertEquals(savedUser.getCountryOfResidence(), country);
        assertEquals(savedUser.getGender(), gender);
        assertEquals(savedUser.getPhoneNumber(), phone);
    }

    /**
     * THIS METHOD TEST IF A USER EXIST WITH A GIVEN USERNAME
     */
    @Test
    public void shouldFindUserByUserName() throws ParseException {
        // METHOD TO TEST
        UserEntity retrievedUser = userRepository.findByUserName(user.getUserName());

        // ASSERT TEST
        assertNotNull(retrievedUser);
        assertThat(retrievedUser.getId()).isNotNull();
        assertEquals(retrievedUser.getUserName(), USERNAME);
        assertEquals(retrievedUser.getBirthDay(), BIRTHDAY);
        assertEquals(retrievedUser.getCountryOfResidence(), COUNTRY_OF_RESIDENCE);
        assertEquals(retrievedUser.getGender(), GENDER);
        assertEquals(retrievedUser.getPhoneNumber(), PHONE);
    }

}
