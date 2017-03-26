package ru.ilka.mac.client;

import ru.ilka.mac.exception.RestaurantServiceException;

import java.util.NoSuchElementException;

/**
 * Здесь могла бы быть ваша реклама +375(29)3880490
 */
public class WaitState implements ClientState {

    @Override
    public void findCashDesk(Client context) {

    }

    @Override
    public void makeDecision(Client context) throws RestaurantServiceException {
        try {
            if (context.getCashDesk().getFirstClient().equals(context)) {
                context.setCurrentState(new InProcessState());
            } else if (context.getWaitingTime() > MAX_WAITING_TIME && context.getCashDesk().getLastClient().equals(context)) {
                context.setCurrentState(new WaitTooLongState());
            }
        }catch (NoSuchElementException e){
            throw new RestaurantServiceException();
        }
    }

    @Override
    public void processing(Client context) {

    }
}
