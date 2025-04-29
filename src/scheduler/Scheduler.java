package scheduler;

import java.util.*;

public class Scheduler {
	
	private List <Process> processList;
	
	public Scheduler() {
		processList = new ArrayList<>();
		
	}
	
	
	public void addProcess(Process process)
	{
		processList.add(process);
		
	}
	
	//FCFS ALGORITHM
	
	public void runFCFS() {
		//sort proceses by arrival time
		
	  Collections.sort(processList, Comparator.comparingInt(Process::getArrival));int currentTime=0;
	
	  for(Process process:processList)
	  {
		  process.markReady(); //mark process as ready
		  
		  process.markRunning(currentTime);//transition to running state
		  
		  process.calculateWaitingTime(); // calculate waiting time for process
		  
		  process.calculateTurnAroundTime(); // calculate Turnaround Time for process
		  
		  //update current time after process finish
		  currentTime+=process.getBurst();
		  process.markFinished(currentTime); // mark process as finished
	  }
	    
	    
	}
	
	public void displayResults()
	{
		for (Process process: processList) {
			System.out.println(process.toString());
		}
	}
	

}
