package ru.ilka.mac.client;

import org.apache.log4j.Logger;
import ru.ilka.mac.exception.RestaurantServiceException;
import ru.ilka.mac.restaurant.CashDesk;
import ru.ilka.mac.restaurant.Restaurant;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * Здесь могла бы быть ваша реклама +375(29)3880490
 */

public class Client extends Thread {
    private static final int SLEEP_TIME = 100;
    private static Logger logger = Logger.getLogger(Client.class);

    private String clientName;
    private int orderAmount;
    private int waitingTime;
    private CashDesk cashDesk;
    private ClientState currentState;
    private static Restaurant restaurant = Restaurant.getInstance();
    private Semaphore semaphore;


    public  Client(String name, int orderAmount, Semaphore sem){
        this.clientName = name;
        this.orderAmount = orderAmount;
        this.semaphore = sem;
        this.currentState = new StartState();

    }

    private void findPriorityCashDesk(){
        try {
            semaphore.acquire();
            currentState.findCashDesk(this);
        }catch (InterruptedException e) {
            e.printStackTrace();
        }
        semaphore.release();

    }

    public void makeDecision(){
        try {
            currentState.makeDecision(this);
        } catch (RestaurantServiceException e) {
            logger.debug("Some troubles with client processing");
        }

    }

    public void waitTooLong(){
        findPriorityCashDesk();
        this.setWaitingTime(0);
        System.out.println(this + " has changed his cashdesk");
    }

    public void processing() throws RestaurantServiceException{
        currentState.processing(this);
    }

    public void run() {
        if(currentState.getClass() == StartState.class) {
            System.out.println(this + "start");
            findPriorityCashDesk();
        }

        try {
            while (currentState.getClass() == WaitState.class) {
                makeDecision();

                if(currentState.getClass() == WaitTooLongState.class) {
                    waitTooLong();
                }
                else if(currentState.getClass() != InProcessState.class) {
                    waitingTime += SLEEP_TIME;
                    TimeUnit.MILLISECONDS.sleep(cashDesk.getServingTime() * cashDesk.getFirstClient().getOrderAmount() + SLEEP_TIME);
                }
                else {
                    TimeUnit.MILLISECONDS.sleep(SLEEP_TIME);
                }
            }
            processing();
        } catch (InterruptedException | RestaurantServiceException e) {
            logger.error("Error in client "+ getClientName()+ " processing ", e);
        }
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public String getClientName() {
        return clientName;
    }

    public int getOrderAmount() {
        return orderAmount;
    }

    public void setOrderAmount(int orderAmount) {
        this.orderAmount = orderAmount;
    }

    public int getWaitingTime() {
        return waitingTime;
    }

    public void setWaitingTime(int waitingTime) {
        this.waitingTime = waitingTime;
    }

    public CashDesk getCashDesk() {
        return cashDesk;
    }

    public void setCashDesk(CashDesk cashDesk) {
        this.cashDesk = cashDesk;
    }

    public ClientState getCurrentState() {
        return currentState;
    }

    public void setCurrentState(ClientState currentState) {
        this.currentState = currentState;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Client client = (Client) o;

        if (orderAmount != client.orderAmount) return false;
        if (waitingTime != client.waitingTime) return false;
        if (!clientName.equals(client.clientName)) return false;
        return !(currentState != null ? !currentState.equals(client.currentState) : client.currentState != null);

    }

    @Override
    public int hashCode() {
        int result = clientName.hashCode();
        result = 31 * result + orderAmount;
        result = 31 * result + waitingTime;
        result = 31 * result + (cashDesk != null ? cashDesk.hashCode() : 0);
        result = 31 * result + (currentState != null ? currentState.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Client{" +
                "ClientName='" + clientName + '\'' +
                "orderAmount='" + orderAmount + '\'' +
                '}';
    }

}
