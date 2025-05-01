package scheduler;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

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
	
	
	private static void printAverages(String title, List<Process> procs) {
	    double avgWait = procs.stream()
	                          .mapToInt(Process::getWaitingTime)
	                          .average()
	                          .orElse(0.0);
	    double avgTurn = procs.stream()
	                          .mapToInt(Process::getTurnAroundTime)
	                          .average()
	                          .orElse(0.0);
	    System.out.printf(
	        "%s – Avg waiting: %.2f, Avg turnaround: %.2f%n%n",
	        title, avgWait, avgTurn
	    );
	}
	
    public static void main(String[] args)throws IOException {
    	
    	List<Process> original = loadProcesses("processes.txt");
    	  
    	
    	
    	//FCFS
    	List<Process> fcfsProcs = original.stream()
    		    .map(Process::new) 
    		    .collect(Collectors.toList());

    		Scheduler fcfs = new Scheduler();
    		fcfsProcs.forEach(fcfs::addProcess);
    		fcfs.runFCFS();
    		fcfs.displayResults("FCFS");
    		printAverages("FCFS", fcfsProcs);
        
        //SJF
    	 List<Process> sjfProcs = original.stream()
    		    .map(Process::new)
    		    .collect(Collectors.toList());
    		Scheduler sjf = new Scheduler();
    		sjfProcs.forEach(sjf::addProcess);
    		sjf.runSJF();
    		sjf.displayResults("SJF");
    		printAverages("SJF", sjfProcs);  
    		
        //PSJF
    	List<Process> psjfProcs = original.stream()
    	        .map(Process::new)
    	        .collect(Collectors.toList());
    	        Scheduler psjf = new Scheduler();
    	    psjfProcs.forEach(psjf::addProcess);
    	    psjf.runPSJF();
    	    psjf.displayResults("PSJF");
    	    printAverages("PSJF", psjfProcs);

    	
    	//Round Robin
    	    List<Process> rrProcs = original.stream()
    	            .map(Process::new)
    	            .collect(Collectors.toList());
    	        Scheduler rr = new Scheduler();
    	        rrProcs.forEach(rr::addProcess);
    	        rr.runRoundRobin(2);
    	        rr.displayResults("Round-Robin q=2");
    	        printAverages("Round-Robin q=2", rrProcs);

        //Priority (non-preemptive)
    	        List<Process> prioProcs = original.stream()
    	                .map(Process::new)
    	                .collect(Collectors.toList());
    	            Scheduler prio = new Scheduler();
    	            prioProcs.forEach(prio::addProcess);
    	            prio.runPriority(false);
    	            prio.displayResults("Priority (non-preemptive)");
    	            printAverages("Priority (non-preemptive)", prioProcs);
        
    }	
       
}
