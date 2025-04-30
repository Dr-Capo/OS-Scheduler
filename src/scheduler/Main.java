package scheduler;

import java.io.*;
import java.util.*;

public class Main {

	public static List<Process> loadProcesses(String filename) throws IOException {
        List<Process> list = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                line = line.trim();
                if (line.isEmpty() || line.startsWith("#")) continue;        
                String[] parts = line.split("\\s+");
                int pid      = Integer.parseInt(parts[0]);
                int arrival  = Integer.parseInt(parts[1]);
                int burst    = Integer.parseInt(parts[2]);
                int priority = Integer.parseInt(parts[3]);
                list.add(new Process(pid, arrival, burst, priority));
            }
        }
        return list;
    }
	
	
	
	
    public static void main(String[] args)throws IOException {
    	
    	List<Process> original = loadProcesses("processes.txt");
    	  
    	Scheduler scheduler=new Scheduler();
    	
    	//FCFS
    	Scheduler fcfs = new Scheduler();
    	original.forEach(p -> fcfs.addProcess(new Process(p)));
    	fcfs.runFCFS();
        fcfs.displayResults("FCFS");
        
        //SJF
    	Scheduler sjf = new Scheduler();
    	original.forEach(p -> sjf.addProcess(new Process(p)));
        sjf.runSJF();
        sjf.displayResults("SJF");
        
        //PSJF
        Scheduler psjf = new Scheduler();
        original.forEach(p -> psjf.addProcess(new Process(p)));
        psjf.runPSJF();
        psjf.displayResults("PSJF");
    	
    	//Round Robin
        Scheduler rr = new Scheduler();
        original.forEach(p -> rr.addProcess(new Process(p)));
        rr.runRoundRobin(2);
        rr.displayResults("Round-Robin q=2");

        //Priority (non-preemptive)
        Scheduler pr = new Scheduler();
        original.forEach(p -> pr.addProcess(new Process(p)));
        pr.runPriority(false);
        pr.displayResults("Priority (non-preemptive)");
        
    }	
       
}
