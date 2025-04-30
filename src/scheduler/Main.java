package scheduler;

public class Main {

    public static void main(String[] args) {
    	
    	Scheduler scheduler=new Scheduler();
    	
        //Create Processes
        Process p1 = new Process(1, 0, 5, 1);  // Arrival at time 0, burst time 5
        Process p2 = new Process(2, 1, 3, 2);  // Arrival at time 1, burst time 3
        Process p3= new Process(3, 2, 8, 1); 
        Process p4 = new Process(4, 3, 6, 3);
        //Process p5 = new Process(5, 10, 6, 2);
        
      // FCFS scheduling demo
       Scheduler fcfs = new Scheduler();
        
       fcfs.addProcess(p1);
       fcfs.addProcess(p2);
       fcfs.addProcess(p3);
       fcfs.addProcess(p4);
       //fcfs.addProcess(p5);

       
       //running FCFS 
       fcfs.runFCFS();
       
       //displaying results
       fcfs.displayResults("FCFS");
       
       
       //RoundRobin Scheduling Demo
       
       Scheduler rr= new Scheduler();
       
       rr.addProcess(new Process(1, 0, 5, 1));
       rr.addProcess(new Process(2, 1, 3, 2));
       rr.addProcess(new Process(3, 2, 8, 1));
       rr.addProcess(new Process(4, 3, 6, 3));
       
       rr.runRoundRobin(2); // time slice=2
       rr.displayResults("Round-Robin q=2");
       
       
       //SJF Scheduling Demo
       Scheduler sjf = new Scheduler();
       sjf.addProcess(new Process(1, 0, 5, 1));
       sjf.addProcess(new Process(2, 1, 3, 2));
       sjf.addProcess(new Process(3, 2, 8, 1));
       sjf.addProcess(new Process(4, 3, 6, 3));
       sjf.runSJF();                     
       sjf.displayResults("SJF");      
    }
}
