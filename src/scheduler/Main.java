package scheduler;

public class Main {

    public static void main(String[] args) {
        // Example of creating a process
        Process p1 = new Process(0, 5);  // Arrival at time 0, burst time 5
        Process p2 = new Process(1, 3);  // Arrival at time 1, burst time 3

        // Example of calculating waiting and turnaround time
        p1.calculateWaitingTime(0);  // Start at time 0
        p1.calculateTurnAroundTime();
        
        p2.calculateWaitingTime(5);  // Start after p1 finishes at time 5
        p2.calculateTurnAroundTime();
        
        // Output the results
        System.out.println("Process 1 - Waiting Time: " + p1.waitingTime + ", Turnaround Time: " + p1.turnAroundTime);
        System.out.println("Process 2 - Waiting Time: " + p2.waitingTime + ", Turnaround Time: " + p2.turnAroundTime);
    }
}
