package org.example.BusinessLogic;

import org.example.model.Server;
import org.example.model.Task;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public class Scheduler
{
    private List<Server> servers;
    private int maxNoServers;
    private int maxTaskPerServer;
    private Strategy strategy;
    public Scheduler(int maxNoServers) {
        this.maxNoServers = maxNoServers;//for maxNoServers
        this.servers = new ArrayList<>();


        //create server obj
        for(int i=0; i< this.maxNoServers; i++)
        {
            Server server = new Server();
            servers.add(server);
            ExecutorService executorService = Executors.newSingleThreadExecutor();
            executorService.execute(server);
        }

//        //create a thread for each server
//        for(Server server: servers)
//        {
//            Thread thread = new Thread(server);
//            thread.start();
//             }
    }
    public void changeStrategy (SelectionPolicy policy){
        if(policy == SelectionPolicy.SHORTEST_QUEUE)
        {
            strategy = new ConcreteStrategyQueue();
        }
        else
        {
            strategy = new ConcreteStrategyTime();
        }
    }
    public void dispatchTask(Task t) {
        //call the strategy  addTask method
        if(strategy != null) {
            strategy.addTask(this.servers, t);
        }else {
            System.out.println("No stratehy set!");
        }
    }
    public List<Server> getServers()
    {
        return servers;
    }
    public int getMaxNoServers ()
    {
        return  maxNoServers;
    }

}
