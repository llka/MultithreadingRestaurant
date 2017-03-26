package ru.ilka.mac.client;

import ru.ilka.mac.client.Client;
import ru.ilka.mac.exception.RestaurantServiceException;
import ru.ilka.mac.restaurant.Restaurant;

/**
 * Здесь могла бы быть ваша реклама +375(29)3880490
 */
public interface ClientState {
    int MAX_WAITING_TIME = 1000;
    void findCashDesk(Client context);
    void makeDecision(Client context) throws RestaurantServiceException;
    void processing(Client context) throws RestaurantServiceException;
}
