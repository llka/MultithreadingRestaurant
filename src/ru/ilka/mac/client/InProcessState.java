package ru.ilka.mac.client;

import org.apache.log4j.Logger;
import ru.ilka.mac.exception.RestaurantServiceException;


import java.util.concurrent.TimeUnit;


/**
 * Здесь могла бы быть ваша реклама +375(29)3880490
 */
public class InProcessState implements ClientState {
    static Logger logger = Logger.getLogger(InProcessState.class);
    @Override
    public void findCashDesk(Client context) {

    }

    @Override
    public void makeDecision(Client context) {

    }

    @Override
    public void processing(Client context) throws RestaurantServiceException {
        try {
            TimeUnit.MILLISECONDS.sleep(context.getCashDesk().getServingTime() * context.getOrderAmount());
        } catch (InterruptedException e) {
            throw new RestaurantServiceException();
        }
        context.getCashDesk().removeFirstClient();
        logger.info(context + "eat");
    }
}
