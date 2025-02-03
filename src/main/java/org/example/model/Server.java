package org.example.model;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;


import static java.lang.Thread.sleep;
public class Server implements Runnable {

    private volatile boolean isRunning = true;
    private BlockingQueue<Task> tasks;
    private AtomicInteger waitingPeriod;
    private int totalWaitingTime; // Total waiting time of all tasks
    private int totalServiceTime; // Total service time of all tasks
    public Server() {
        //initialize queue and waitingPeriod
        this.tasks = new ArrayBlockingQueue<>(10000);
        this.waitingPeriod = new AtomicInteger(0);
        this.totalWaitingTime=0;
        this.totalServiceTime=0;
    }

    public void addTask(Task newTask) {
        tasks.add(newTask); // add task to queue
        waitingPeriod.addAndGet((int)newTask.getServiceTime()); // increment waitingPeriod
    }

    @Override
    public void run() {
        while(isRunning)
        {
            for(Task task: tasks)
            {
                task.incrementWaitingTime();;
            }
            Task currentTask = tasks.peek();
            if(currentTask!=null)
            {
                currentTask.decrementServiceTime();
                this.waitingPeriod.getAndAdd(-1);
                if(currentTask.getServiceTime()==0)
                {
                    totalWaitingTime += currentTask.getWaitingTime(); // Add task's waiting time to total
                    totalServiceTime += currentTask.getOriginalServiceTime(); // Add task's original service time to total
                    tasks.remove();
                }
            }
            try{
                sleep(1000);
            }catch(Exception e){
                e.printStackTrace();
            }
        }
    }

    public BlockingQueue<Task> getTasks() {
        return tasks;
    }

    public int getWaitingPeriod() {
        return waitingPeriod.get();
    }

    public int getTotalWaitingTime() {
        return totalWaitingTime;
    }
    public int getTotalServiceTime(){
        return totalServiceTime;
    }
    //    public Task[] getTasks() {
//    }
public void stop(){
    isRunning = false;
}


}
