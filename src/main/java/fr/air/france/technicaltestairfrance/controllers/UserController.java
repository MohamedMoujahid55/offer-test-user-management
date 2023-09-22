package fr.air.france.technicaltestairfrance.controllers;

import fr.air.france.technicaltestairfrance.dtos.UserDto;
import fr.air.france.technicaltestairfrance.services.IUserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * This is a sample web controller.
 *
 * <p>This class provides endpoints for User Entity '/api/users'.
 * It interacts with the service layer and performs various operations.
 *
 * @author Mohamed MOUJAHID
 * @version 1.0
 */

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    IUserService userService;

    /**
     * This endpoint register a use.
     *
     * @param UserDto.
     * @return UserDto, or an ErrorMessage if not found.
     */
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserDto> registerUser(@Valid @RequestBody UserDto userDto) throws Exception {
        try {
            return new ResponseEntity<>(userService.registerUser(userDto), HttpStatus.CREATED);
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

    /**
     * This method retrieves a user by their ID.
     *
     * @param userName.
     * @return UserDto, or an error ErrorMessage if not found.
     */
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserDto> getUserDetails(@RequestParam(name = "user_name") String userName) throws Exception {
        try {
            return new ResponseEntity<>(userService.displayUser(userName), HttpStatus.OK);
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }
}
