package ru.ilka.mac.client;

import ru.ilka.mac.restaurant.CashDesk;

import java.util.Comparator;
import java.util.List;

/**
 * Здесь могла бы быть ваша реклама +375(29)3880490
 */
public class WaitTooLongState implements ClientState{
    private static final int BEST_CASHDESK_POSITION = 0;

    @Override
    public void findCashDesk(Client context) {
        List<CashDesk> cashDesks = context.getRestaurant().getCashDeskList();
        cashDesks.sort(new ClientsComparator());

        context.getCashDesk().removeLastClient();

        context.setCashDesk(cashDesks.get(BEST_CASHDESK_POSITION));
        context.getCashDesk().addClient(context);

        context.setCurrentState(new WaitState());
    }

    @Override
    public void makeDecision(Client context) {

    }

    @Override
    public void  processing(Client context) {

    }

    private class ClientsComparator implements Comparator<CashDesk> {
        @Override
        public int compare(CashDesk o1, CashDesk o2) {
            int size1 = o1.getQueue().size();
            int size2 = o2.getQueue().size();

            if(size1 > size2) {
                return 1;
            }
            else if(size1 < size2){
                return -1;
            }
            else {
                return 0;
            }
        }
    }
}
