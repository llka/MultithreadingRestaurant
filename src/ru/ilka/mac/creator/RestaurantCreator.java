package ru.ilka.mac.creator;

import ru.ilka.mac.reader.RestaurantReader;
import ru.ilka.mac.restaurant.Restaurant;

import java.util.List;

/**
 * Здесь могла бы быть ваша реклама +375(29)3880490
 */
public class RestaurantCreator {

    static final int CASH_ID_POSITION = 0;
    static final int SERVING_TIME_POSITION = 1;

    public Restaurant createRestaurant(String filePath){
        RestaurantReader restaurantReader = new RestaurantReader();
        Restaurant restaurant = Restaurant.getInstance();
        List<List<Integer>> cashDeskInfo = restaurantReader.readRestaurant(filePath);

        for (int i = 0; i < cashDeskInfo.size() ; i++) {
            restaurant.createCashDesk(cashDeskInfo.get(i).get(CASH_ID_POSITION), cashDeskInfo.get(i).get(SERVING_TIME_POSITION));
        }

        return restaurant;
    }
}
