package scheduler;

import java.lang.Thread.State;

public class Process {
	
	//PCB:
	// identity 
	private final int pid; //unique id
	private final String name;
	
	//State
	public enum State{NEW, READY, RUNNING, WAITING, TERMINATED}
	private State state= State.NEW;
	
	//scheduling timing
    private final int arrivalTime;
    private final int burstTime;
    private int remainingTime;
    private int priority;
    
    private int start=-1;
    private int finish=-1;

    // Constructor to initialize arrival and burst times
    public Process(int pid, int arrival, int burst, int priority) {
        this.pid=pid;
        this.name= "P"+ pid;
        this.arrivalTime=arrival;
        this.burstTime=burst;
        this.priority=priority;
        this.remainingTime=burst;
        
    }
    
    //getters
    public int getPid() {return pid;}
    public String getName() {return name;}
    public int getArrival() {return arrivalTime;}
    public int getBurst()       { return burstTime; }
    public int getRemaining()   { return remainingTime; }
    public int getPriority()    { return priority; }
    public State getState()     { return state; }
    
    //helper methods
    public void markReady() {state= State.READY;}
    public void markRunning(int clk) {state=State.RUNNING; if (start<0) start=clk;}
    public void cpuTick() {if(remainingTime>0)remainingTime--;}
    boolean isDone() {return remainingTime == 0;}
    void markFinished(int clk) {state=State.TERMINATED; finish =clk; remainingTime=0;}

    // Method to calculate waiting time
    public int calculateWaitingTime() {
       return start-arrivalTime;
    }
   

    // Method to calculate turnaround time
    public int calculateTurnAroundTime() {
       return finish - arrivalTime;
    }
    
    @Override
    public String toString() {
        return String.format(
            "%-3s %s arr=%2d burst=%2d rem=%2d pr=%2d wait=%2d ta=%2d",
            name, state, arrivalTime, burstTime, remainingTime, priority,
            (start < 0 ? -1 : calculateWaitingTime()), 
            (finish < 0 ? -1 : calculateTurnAroundTime())
        );
    }
    
}
