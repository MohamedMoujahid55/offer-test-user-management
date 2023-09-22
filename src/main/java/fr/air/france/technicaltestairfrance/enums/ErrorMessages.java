package fr.air.france.technicaltestairfrance.enums;

/**
 * This is an Enum.
 *
 * <p>This enum permit to customize error messages.
 *
 * @author MOUJAHID Mohamed
 * @version 1.0
 */
public enum ErrorMessages {
    MISSING_REQUIRED_FIELD("Missing required field."),
    RECORD_ALREADY_EXISTS("Record already exists."),
    INTERNAL_SERVER_ERROR("Internal server error."),
    DOES_NOT_MATCH_WITH_OUR_RECORDS("Does not match with our records "),
    NO_RECORD_FOUND("Record with provided information has not been found");

    private String errorMessage;

    private ErrorMessages(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

}