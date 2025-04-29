package scheduler;

public class Main {

    public static void main(String[] args) {
    	
    	Scheduler scheduler=new Scheduler();
    	
        // Example of creating a process
        Process p1 = new Process(1, 0, 5, 1);  // Arrival at time 0, burst time 5
        Process p2 = new Process(2, 4, 3, 2);  // Arrival at time 1, burst time 3
        Process p3= new Process(3, 2, 4, 1); 
        Process p4 = new Process(4, 9, 2, 3);
        Process p5 = new Process(5, 7, 6, 2);
        
      //Adding Processes to scheduler
       scheduler.addProcess(p1);
       scheduler.addProcess(p2);
       scheduler.addProcess(p3);
       scheduler.addProcess(p4);
       scheduler.addProcess(p5);

       
       //running FCFS 
       scheduler.runFCFS();
       
       //displaying results
       scheduler.displayResults();
       
    }
}
