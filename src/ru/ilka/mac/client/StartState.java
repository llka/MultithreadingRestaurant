package ru.ilka.mac.client;

import ru.ilka.mac.restaurant.CashDesk;

import java.util.Comparator;
import java.util.List;

/**
 * Здесь могла бы быть ваша реклама +375(29)3880490
 */
public class StartState implements ClientState {
    private static final int BEST_CASHDESK_POSITION = 0;

    @Override
    public void findCashDesk(Client context) {
        List<CashDesk> cashDesks = context.getRestaurant().getCashDeskList();

        CashDesk priorityCashDesk = cashDesks.get(BEST_CASHDESK_POSITION);
        int min = priorityCashDesk.getQueue().size() ;
        for (int i = 1; i < cashDesks.size() ; i++) {
            if(cashDesks.get(i).getQueue().size() <= min){
                priorityCashDesk = cashDesks.get(i);
            }
        }

        context.setCashDesk(priorityCashDesk);
        context.getCashDesk().addClient(context);

        System.out.println(context + " in " + context.getCashDesk());
        context.setCurrentState(new WaitState());
    }

    @Override
    public void makeDecision(Client context) {
        findCashDesk(context);
    }

    @Override
    public void  processing(Client context) {

    }
}
