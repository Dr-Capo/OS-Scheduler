package scheduler;

public class Process {
    int arrivalTime;
    int burstTime;
    int waitingTime;
    int turnAroundTime;

    // Constructor to initialize arrival and burst times
    public Process(int arrival, int burst) {
        this.arrivalTime = arrival;
        this.burstTime = burst;
        this.waitingTime = 0;
        this.turnAroundTime = 0;
    }

    // Method to calculate waiting time
    public void calculateWaitingTime(int startTime) {
        this.waitingTime = startTime - arrivalTime;
    }

    // Method to calculate turnaround time
    public void calculateTurnAroundTime() {
        this.turnAroundTime = waitingTime + burstTime;
    }
}
