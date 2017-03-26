package ru.ilka.mac.restaurant;

import org.apache.log4j.Logger;
import ru.ilka.mac.client.Client;
import ru.ilka.mac.client.InProcessState;
import ru.ilka.mac.exception.RestaurantServiceException;
import java.util.ArrayDeque;
import java.util.concurrent.TimeUnit;

/**
 * Здесь могла бы быть ваша реклама +375(29)3880490
 */
public class CashDesk{
    private long cashDeskId;
    private int servingTime;
    private ArrayDeque<Client> queue;

    public CashDesk(long cashDeskId, int servingTime) {
        this.cashDeskId = cashDeskId;
        this.servingTime = servingTime;
        this.queue = new ArrayDeque<>();
    }

    public void addClient(Client client){
        queue.addLast(client);
    }

    public void removeLastClient(){
        queue.removeLast();
    }

    public void removeFirstClient(){
        queue.removeFirst();
    }

    public Client getLastClient(){
        return queue.getLast();
    }

    public Client getFirstClient(){
        return queue.getFirst();
    }

    public long getCashDeskId() {
        return cashDeskId;
    }

    public void setCashDeskId(long cashDeskId) {
        this.cashDeskId = cashDeskId;
    }

    public int getServingTime() {
        return servingTime;
    }

    public void setServingTime(int servingTime) {
        this.servingTime = servingTime;
    }

    public ArrayDeque<Client> getQueue() {
        return queue;
    }

    public void setQueue(ArrayDeque<Client> queue) {
        this.queue = queue;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CashDesk cashDesk = (CashDesk) o;

        if (cashDeskId != cashDesk.cashDeskId) return false;
        if (servingTime != cashDesk.servingTime) return false;
        return  (queue != null ? !queue.equals(cashDesk.queue) : cashDesk.queue != null);
    }

    @Override
    public int hashCode() {
        int result = (int) (cashDeskId ^ (cashDeskId >>> 32));
        result = 31 * result + servingTime;
        result = 31 * result + (queue != null ? queue.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "CashDesk{" +
                "cashDeskId=" + cashDeskId +
                ", servingTime=" + servingTime +
                '}';
    }
}
