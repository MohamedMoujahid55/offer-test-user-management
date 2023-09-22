package fr.air.france.technicaltestairfrance.entities;

import fr.air.france.technicaltestairfrance.enums.UserGender;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * This is a sample Spring class entity.
 *
 * <p>This class permit to create a table named 'user' & persist user entity.
 *
 * @author MOUJAHID Mohamed
 * @version 1.0
 */
@Entity
@Table(name = "users")
@Data @NoArgsConstructor @AllArgsConstructor
@Builder
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(name = "user_name", nullable = false, unique = true)
    private String userName;

    @Column(name = "birth_day", nullable = false)
    private Date birthDay;

    @Column(name = "country_of_residence", nullable = false)
    private String countryOfResidence;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Enumerated(EnumType.STRING)
    private UserGender gender;
}
