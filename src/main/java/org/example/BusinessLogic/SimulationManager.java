package org.example.BusinessLogic;

import org.example.GUI.Design;
import org.example.model.Server;
import org.example.model.Task;

import javax.swing.*;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class SimulationManager extends Thread
{
    private volatile boolean isRunning = true;
    //data read from UI
    private int timeLimit;
    private int maxServiceTime;
    private int minServiceTime;
    private int minArrivalTime;
    private int maxArrivalTime;
    private int nbOfQueues;
    private int nbOfClients;
    private int taskIdCounter = 1;
    public float averageWaitingTime=0;  //Average waiting time of all tasks
    public float averageServiceTime=0; //Average service time of all tasks
    private SelectionPolicy selectionPolicy=SelectionPolicy.SHORTEST_TIME;

    //entity responsable with queue management  and client distribution
    private Scheduler scheduler; //queue management and clients distribution
    private CopyOnWriteArrayList<Task> generetedTasks;
    private Design design;
    private int peakHour=0;
    private int peakClients=0;

    //private Simulation simulation;
    public SimulationManager(Design design) {
        this.design = design;
        this.nbOfClients = design.getNbClientsText();
        this.nbOfQueues = design.getNbQueuesText();
        this.timeLimit = design.getSimuationIntervalText();
        this.minArrivalTime = design.getMinArrTimeText();
        this.maxArrivalTime = design.getMaxArrTimeText();
        this.minServiceTime = design.getMinServiceTimeText();
        this.maxServiceTime = design.getMaxServiceTimeText();
        this.generetedTasks = new CopyOnWriteArrayList<Task>();

    }

    public void generateNRandomTasks ()
    {
        //generate N random Tasks
        generetedTasks.clear();
        for (int i = 0; i < nbOfClients; i++) {
            int serviceTime = (int)(Math.random()*(maxServiceTime - minServiceTime)+minServiceTime);
            int arrivalTime = (int)(Math.random()*(maxArrivalTime - minArrivalTime)+minArrivalTime);
            Task task = new Task(i+1, arrivalTime, serviceTime);
            this.generetedTasks.add(task);
        }
        generetedTasks.sort(new Comparator<Task>() {
            @Override
            public int compare(Task task1, Task task2) {

                return Integer.compare(task1.getArrivalTime(), task2.getArrivalTime());
            }
        });
        //System.out.println("Generated tasks: "+generetedTasks);
    }

    public void stopSimulation(){
        isRunning = false;
    }


    public List<Task> addGeneratedTasksToGUI(){
        return generetedTasks;
    }

    public void startButton()
    {
        generateNRandomTasks();
        scheduler = new Scheduler(nbOfQueues);
        scheduler.changeStrategy(selectionPolicy);
        this.start();
    }


    @Override
    public void run() {
        int currentTime = 0;

        //iterate generatedTask list
        generateNRandomTasks();
        while(currentTime <= timeLimit)
        {
            int currentClients = 0;
            //pick task that have the arrivalTime = with the currentTime
            List<Task> toRemove = new ArrayList<>();
            for(Task task:generetedTasks)
            {
                if(task.getArrivalTime() == currentTime)
                {
                    scheduler.dispatchTask(task);
                    toRemove.add(task);
                }
            }
            generetedTasks.removeAll(toRemove);

            for(Server server: scheduler.getServers()) {
                currentClients += server.getTasks().size();
            }
            if(currentClients > peakClients)
            {
                peakClients = currentClients;
                peakHour = currentTime;
            }
            //update UI frame
            for(Server server: scheduler.getServers())
            {
                averageWaitingTime+=server.getTotalWaitingTime(); // Sum up total waiting times from all servers
                averageServiceTime +=server.getTotalServiceTime(); // Sum up total service times from all servers
            }
            if(nbOfClients!=0)
            {
                averageWaitingTime=averageWaitingTime/nbOfClients; // Calculate average waiting time
                averageServiceTime=averageServiceTime/nbOfClients; // Calculate average service time
            }

            design.setAverageTimeLabel("Average Waiting Time: " + averageWaitingTime);
            design.setAverageServiceTimeLabel("Average Service Time: "+ averageServiceTime);
            design.setPeakHourLabel("Peak Hour: "+peakHour);
            System.out.println("Average Waiting time: "+averageWaitingTime);
            System.out.println("Average Service time: "+averageServiceTime);
            System.out.println("PeakHour: "+peakHour);

            try (FileWriter writer = new FileWriter("DataOut.txt", true)) { // "true" for append mode
                design.setCurrentTimeLabel("Current Time: " + currentTime);
                design.setAverageTimeLabel("Average Waiting Time: " + averageWaitingTime);
                design.getQueuesArea().setText(""); // clear the queues area
                design.addGeneretedTasks(generetedTasks);

                writer.append("\n");
                writer.write("Time " + currentTime + "\n");
                writer.write("Waiting Clients:\n");

                for(Task task: this.generetedTasks) {
                    writer.append(task.toString());
                }

                int i = 1;
                for(Server server: scheduler.getServers()) {
                    writer.append("\n");
                    design.getQueuesArea().append("Queue" + i + ":");
                    writer.append("Queue" + i + ": ");

                    for(Task task: server.getTasks()) {
                        design.getQueuesArea().append(task.toString() + "; ");
                        writer.append(task.toString()+"; ");
                    }

                    writer.append("\n");
                    i++;
                }
                writer.append("Average Waiting Time: " + averageWaitingTime);
                writer.append("Average Service Time: " + averageServiceTime);

            } catch(IOException e) {
                e.printStackTrace();
            }
            currentTime++;
            try{
                Thread.sleep(1000);  //wait an interval of 1 sec
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        try{
            for(Server server: scheduler.getServers())
            {
                server.stop();
            }
            this.stopSimulation();
        }catch (Exception e)
        {
            e.printStackTrace();
        }
    }


}
