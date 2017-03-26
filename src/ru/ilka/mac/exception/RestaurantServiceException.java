package ru.ilka.mac.exception;

/**
 * Здесь могла бы быть ваша реклама +375(29)3880490
 */
public class RestaurantServiceException extends Exception {
    public RestaurantServiceException() {
    }

    public RestaurantServiceException(String message) {
        super(message);
    }

    public RestaurantServiceException(String message, Throwable cause) {
        super(message, cause);
    }

    public RestaurantServiceException(Throwable cause) {
        super(cause);
    }
}
