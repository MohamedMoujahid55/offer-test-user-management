package fr.air.france.technicaltestairfrance.exceptions;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * This is a sample Spring class.
 *
 * <p>This class is for error messages .
 *
 * @author MOUJAHID Mohamed
 * @version 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ErrorMessage {
    private Date timestamp;
    private String message;
    private String details;
}
