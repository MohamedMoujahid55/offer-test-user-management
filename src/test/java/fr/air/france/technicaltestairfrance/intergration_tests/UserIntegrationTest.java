package fr.air.france.technicaltestairfrance.intergration_tests;

import fr.air.france.technicaltestairfrance.dtos.UserDto;
import fr.air.france.technicaltestairfrance.enums.UserGender;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * USER INTEGRATION TEST : FOR TESTING ALL USER BEHAVIOR
 *
 * @author MOUJAHID Mohamed
 * @version 1.0
 */
@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = RANDOM_PORT)
public class UserIntegrationTest {


    @Autowired
    private TestRestTemplate restTemplate = new TestRestTemplate();

    private final String USERNAME = "MOUJAHID";
    private final Date BIRTHDAY=new SimpleDateFormat("dd/MM/yyyy").parse("31/12/1998");
    private final String COUNTRY_OF_RESIDENCE = "France";
    private final String PHONE = "+3361234565";
    private final UserGender GENDER = UserGender.MALE;

    public UserIntegrationTest() throws ParseException {
    }

    /**
     * This test method dedicated to test the happy path scenario of the user registration api
     * that should return a user registered successfully
     */
    @Test
    void shouldReturnRegisteredUser() {
        // initialisation
        UserDto userDto = UserDto.builder()
                .userName(USERNAME)
                .birthDay(BIRTHDAY)
                .phoneNumber(PHONE)
                .countryOfResidence(COUNTRY_OF_RESIDENCE)
                .gender(GENDER)
                .build();
        // CALL
        ResponseEntity<UserDto> response = restTemplate.postForEntity("/api/users", userDto, UserDto.class);
        // assert
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().getUserName()).isEqualTo(USERNAME);
        assertThat(response.getBody().getCountryOfResidence()).isEqualTo(COUNTRY_OF_RESIDENCE);
        assertThat(response.getBody().getPhoneNumber()).isEqualTo(PHONE);
        assertThat(response.getBody().getGender()).isEqualTo(GENDER);
    }

    /**
     * This test method dedicated to test the scenario when passing invalid args for the user registration api
     * that should return a bad request error
     */
    @Test
    void shouldReturnErrorMessageWhenMissingAttributeForRegisteredUser() {
        // initialisation
        UserDto userDto = UserDto.builder()
                .birthDay(BIRTHDAY)
                .phoneNumber(PHONE)
                .countryOfResidence(COUNTRY_OF_RESIDENCE)
                .gender(GENDER)
                .build();
        // CALL
        ResponseEntity<?> response = restTemplate.postForEntity("/api/users", userDto, UserDto.class);
        // assert
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
        assertThat(response.getBody()).isNotNull();
    }

    /**
     * This test method dedicated to test the happy path scenario of get user details by username API
     * that should return a user details successfully
     */
    @Test
    void getUserReturnsUserDetailsByUsername() {
        // initialisation
        UserDto userDto = UserDto.builder()
                .userName("LUCIE")
                .birthDay(BIRTHDAY)
                .phoneNumber(PHONE)
                .countryOfResidence(COUNTRY_OF_RESIDENCE)
                .gender(GENDER)
                .build();
        // CALL
        restTemplate.postForEntity("/api/users", userDto, UserDto.class);

        // Call
        ResponseEntity<UserDto> response = restTemplate.getForEntity("/api/users?user_name="+"LUCIE", UserDto.class);
        // assert
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().getUserName()).isNotNull();
    }

    /**
     * This test method dedicated to test the scenario of get the user details api with unknown user name
     * that should return a user not found error with appropriate message.
     */
    @Test
    void getUserByUserNameReturnsNotFoundWhenUserNotFound() {
        // act
        ResponseEntity<UserDto> response = restTemplate.getForEntity("/api/users?user_name="+"test", UserDto.class);
        // assert
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR);
        assertThat(response.getBody()).isNotNull();
    }
}
