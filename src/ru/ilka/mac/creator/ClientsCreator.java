package ru.ilka.mac.creator;

import ru.ilka.mac.client.Client;
import ru.ilka.mac.reader.ClientsReader;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Semaphore;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Здесь могла бы быть ваша реклама +375(29)3880490
 */
public class ClientsCreator {

    static final int MIN_AMOUNT = 1;
    static final int UPPER_BOUND_AMOUNT = 20;
    static final int DOORS = 1;  /* number of clients, who can open restaurant's door immediately at the same time */

    public List<Client> createClients(String filePath){
        ClientsReader clientsReader = new ClientsReader();
        List<String> names = clientsReader.readClients(filePath);
        List<Client> clients = new ArrayList<>();
        Semaphore semaphore = new Semaphore(DOORS);

        names.forEach(i -> clients.add(new Client(i, ThreadLocalRandom.current().nextInt(MIN_AMOUNT, UPPER_BOUND_AMOUNT),semaphore)));
        System.out.println("clients in creator" + clients);

        return Collections.unmodifiableList(clients);
    }
}
