package scheduler;

public class Main {

    public static void main(String[] args) {
        // Example of creating a process
        Process p1 = new Process(1, 0, 5, 1);  // Arrival at time 0, burst time 5
        Process p2 = new Process(2, 1, 3, 2);  // Arrival at time 1, burst time 3

        // Example of calculating waiting and turnaround time
        p1.markReady();//set to ready state
        p1.markRunning(0);//start at time 
        p1.calculateWaitingTime();  // Start at time 0
        p1.calculateTurnAroundTime();
        
        p2.markReady();
        p2.markRunning(5);//start after p1 finishes at 5
        p2.calculateWaitingTime();  
        p2.calculateTurnAroundTime();
        
        // Output the results
        System.out.println(p1.toString());
        System.out.println(p2.toString());
    }
}
