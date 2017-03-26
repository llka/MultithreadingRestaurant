package ru.ilka.mac.main;

import ru.ilka.mac.client.Client;
import ru.ilka.mac.creator.ClientsCreator;
import ru.ilka.mac.creator.RestaurantCreator;
import ru.ilka.mac.restaurant.Restaurant;

import java.util.List;

public class Main {

    public static void main(String[] args) {
        RestaurantCreator restaurantCreator = new RestaurantCreator();
        ClientsCreator clientsCreator = new ClientsCreator();
        Restaurant restaurant = restaurantCreator.createRestaurant("data/inputR.txt");
        List<Client> clients = clientsCreator.createClients("data/inputC.txt");

        clients.forEach(client -> client.start());
    }
}
