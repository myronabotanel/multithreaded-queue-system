package org.example.BusinessLogic;

import org.example.BusinessLogic.Strategy;
import org.example.model.Server;
import org.example.model.Task;

import java.util.List;

public class ConcreteStrategyTime implements Strategy {

    @Override
    public void addTask(List<Server> servers, Task task) {
        int min = Integer.MAX_VALUE;
        Server minServer = null;
        for (Server server : servers) {
            if (min > server.getWaitingPeriod()) {
                min = server.getWaitingPeriod();
                minServer = server;
            }
        }
        if (minServer != null) {
            minServer.addTask(task);
            System.out.println("Added client to queue: ");
            for (Task t : minServer.getTasks()) {
                System.out.println(t.toString());
            }
            System.out.println("\n");
        } else {
            System.out.println("No server available.");
        }
    }
}
