package ru.ilka.mac.restaurant;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.ReentrantLock;


/**
 * Здесь могла бы быть ваша реклама +375(29)3880490
 */
/* Bill Pugh Singleton */
public class Restaurant {

    private List<CashDesk> cashDeskList;
    /* if it's not Bill Pugh's singleton */
    private static Restaurant instance = null;
    private static ReentrantLock lock = new ReentrantLock();
    private static AtomicBoolean flag = new AtomicBoolean();
    /* for uniqueness  */


    /*
    private static class SingletonHolder{
        private static final Restaurant instance = new Restaurant();
    }

    public static Restaurant getInstance(){
             return SingletonHolder.instance;
    }
    */

    private Restaurant() {
        cashDeskList = new ArrayList<>();
    }

    /* for uniqueness if it's not Bill Pugh's singleton */
    public static Restaurant getInstance() {
        if (!flag.get())
        {
            lock.lock();
            instance = new Restaurant();
            flag.set(true);
            lock.unlock();
        }
        return instance;
    }

    public void createCashDesk(long id, int time) {
        cashDeskList.add(new CashDesk(id, time));
    }

    public List<CashDesk> getCashDeskList() {
        return cashDeskList;
    }

    public void setCashDeskList(List<CashDesk> cashDeskList) {
        this.cashDeskList = cashDeskList;
    }
}
