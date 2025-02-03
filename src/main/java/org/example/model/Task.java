package org.example.model;

import java.util.concurrent.atomic.AtomicInteger;

public class Task {
    private int ID;
    private int arrivalTime;
    private int serviceTime;
    private int originalServiceTime;
    private int  waitingTime;  //timpul pe care o sarcina il petrece asteptand intr o coada

    public Task(int ID, int arrivalTime, int serviceTime){
        this.ID=ID;
        this.arrivalTime=arrivalTime;
        this.serviceTime = serviceTime;
        this.waitingTime = 0;
        this.originalServiceTime = serviceTime;
    }

    public int getID(){
        return this.ID;}
    public int getArrivalTime(){
        return  this.arrivalTime;
    }
    public int getServiceTime(){
        return this.serviceTime;
    }
    public void setID(int ID){
        this.ID=ID;
    }
    public void setArrivalTime(int arrivalTime){
        this.arrivalTime = arrivalTime;
    }
    public void setServiceTime(int serviceTime){
        this.serviceTime = serviceTime;
    }

    public void decrementServiceTime (){
       this.serviceTime--;
    }
    public void incrementServiceTime() {
        this.serviceTime++;
    }
    public int getOriginalServiceTime()
    {
        return originalServiceTime;
    }

    public void incrementWaitingTime(){
        this.waitingTime++;
    }  // 1 unitate de fiecare dată când este apelată.
    //se intampla daca o sarcina e in coada si nu e procesata.
    public int getWaitingTime() {
        return this.waitingTime;}// returnează valoarea curentă a waitingTime

    public String toString() {
        return "(" +ID+ "," +arrivalTime+ "," +serviceTime+ ") ";
    }

}

