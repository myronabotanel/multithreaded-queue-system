package org.example.BusinessLogic;

import org.example.BusinessLogic.Strategy;
import org.example.model.Server;
import org.example.model.Task;

import java.util.List;



/**
 * ConcreteStrategyQueue este o implementare a interfeței Strategy și reprezintă o strategie
 * pentru adăugarea unei sarcini (Task) la coada serverului cu cel mai mic număr de sarcini.
 */
public class ConcreteStrategyQueue implements Strategy
{

    @Override
    public void addTask(List<Server> servers, Task task)
    {
        int min = Integer.MAX_VALUE;
        Server minServer = servers.get(1);
        for(Server server:servers)
        {
            if(server.getTasks().size() < min){
                min=server.getTasks().size();
                minServer = server;
            }
        }
        minServer.addTask(task);
        System.out.println("Added client to queue: ");
        for(Task t: minServer.getTasks())
        {
            System.out.println(t.toString());
        }
        System.out.println("\n");
    }

}
