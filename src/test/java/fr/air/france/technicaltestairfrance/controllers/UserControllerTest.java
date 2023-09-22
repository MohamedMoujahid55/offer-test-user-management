package fr.air.france.technicaltestairfrance.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import fr.air.france.technicaltestairfrance.dtos.UserDto;
import fr.air.france.technicaltestairfrance.enums.ErrorMessages;
import fr.air.france.technicaltestairfrance.enums.UserGender;
import fr.air.france.technicaltestairfrance.exceptions.UserException;
import fr.air.france.technicaltestairfrance.services.IUserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * CLASS TEST : FOR TESTING USER CONTROLLER
 *
 * @author MOUJAHID Mohamed
 * @version 1.0
 */
@ExtendWith(SpringExtension.class)
@WebMvcTest(UserController.class)
public class UserControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private IUserService userService;

    ObjectMapper objectMapper = new ObjectMapper();

    private final String USERNAME = "MOUJAHID";
    private final Date BIRTHDAY=new SimpleDateFormat("dd/MM/yyyy").parse("31/12/1998");
    private final String COUNTRY_OF_RESIDENCE = "France";
    private final String PHONE = "+3361234565";
    private final UserGender GENDER = UserGender.MALE;

    public UserControllerTest() throws ParseException {
    }

    /**
     * THIS is FOR TESTING REGISTER A USER ENDPOINT
     */
    @Test
    public void shouldRegisterUser() throws Exception {
        // prepare a mock for save method in user service class
        UserDto userDto = UserDto.builder()
                .userName(USERNAME)
                .birthDay(BIRTHDAY)
                .phoneNumber(PHONE)
                .countryOfResidence(COUNTRY_OF_RESIDENCE)
                .gender(GENDER)
                .build();
        // mock
        given(userService.registerUser(userDto)).willReturn(userDto);

        // invoke and check the received response
        byte[] jsonBytes = objectMapper.writeValueAsBytes(userDto);

        mockMvc.perform(post("/api/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonBytes)
                )
                .andExpect(status().isCreated())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.userName").value(USERNAME))
                .andExpect(jsonPath("$.countryOfResidence").value(COUNTRY_OF_RESIDENCE))
                .andExpect(jsonPath("$.phoneNumber").value(PHONE))
                .andExpect(jsonPath("$.gender").value(GENDER.name()));
    }

    /**
     * THIS is FOR TESTING REGISTER A USER ENDPOINT
     * -> Missing attribute TEST
     */
    @Test
    public void shouldReturnErrorMessageWhenRequiredAttributeIsMissing() throws Exception {
        // prepare a mock for save method in user service class
        UserDto userDto = UserDto.builder()
                .birthDay(BIRTHDAY)
                .phoneNumber(PHONE)
                .countryOfResidence(COUNTRY_OF_RESIDENCE)
                .gender(GENDER)
                .build();
        // mock
        given(userService.registerUser(userDto)).willReturn(userDto);

        // invoke and check the received response
        byte[] jsonBytes = objectMapper.writeValueAsBytes(userDto);

        mockMvc.perform(post("/api/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonBytes)
                )
                .andExpect(status().isBadRequest())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.userName").value("User name is mandatory"));
    }

    /**
     * THIS is FOR TESTING GET A USER DETAILS ENDPOINT
     *
     */
    @Test
    public void shouldRetrieveUserByUsername() throws Exception {
        // prepare a mock for save method in user service class
        UserDto userDto = UserDto.builder()
                .userName(USERNAME)
                .birthDay(BIRTHDAY)
                .phoneNumber(PHONE)
                .countryOfResidence(COUNTRY_OF_RESIDENCE)
                .gender(GENDER)
                .build();
        // mock
        given(userService.displayUser(USERNAME)).willReturn(userDto);

        // invoke and check the received response
        mockMvc.perform(get("/api/users?user_name="+USERNAME))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.userName").value(USERNAME))
                .andExpect(jsonPath("$.countryOfResidence").value(COUNTRY_OF_RESIDENCE))
                .andExpect(jsonPath("$.phoneNumber").value(PHONE))
                .andExpect(jsonPath("$.gender").value(GENDER.name()));
    }

    /**
     * THIS is FOR TESTING REGISTER A USER ENDPOINT
     * => USER NOT FOUND CASE
     *
     */
    @Test
    public void shouldReturnErrorWhenUserNotFound() throws Exception {

        // mock
        given(userService.displayUser(USERNAME))
                .willThrow(new UserException(ErrorMessages.NO_RECORD_FOUND.getErrorMessage()));

        // invoke and check the received response
        mockMvc.perform(get("/api/users?user_name="+USERNAME))
                .andExpect(status().isInternalServerError())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.message").value("Record with provided information has not been found"));
    }

}
