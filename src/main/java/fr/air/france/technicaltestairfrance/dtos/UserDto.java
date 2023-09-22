package fr.air.france.technicaltestairfrance.dtos;

import fr.air.france.technicaltestairfrance.annotations.CustomerTypeSubset;
import fr.air.france.technicaltestairfrance.enums.UserGender;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * This is a sample Spring class.
 *
 * <p>This class DTO permit to map only needed attributes.
 *
 * @author MOUJAHID Mohamed
 * @version 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor @Builder
public class UserDto {
    @NotBlank(message = "User name is mandatory")
    @Size(min = 3, message = "User name should have at least 3 characters")
    private String userName;

    @NotNull(message = "Birth day is mandatory")
    private Date birthDay;

    @NotBlank(message = "Country of residence day is mandatory")
    @Size(min = 3, message = "Country of residence should have at least 3 characters")
    private String countryOfResidence;

    @Pattern(regexp = "^[\\+]?[(]?[0-9]{3}[)]?[-\\s\\.]?[0-9]{3}[-\\s\\.]?[0-9]{4,6}$",
            message = "Phone number should respect phone patterns ")
    private String phoneNumber;

    @CustomerTypeSubset(anyOf = {UserGender.MALE, UserGender.FEMALE, UserGender.OTHER})
    private UserGender gender;
}
