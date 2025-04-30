package scheduler;

import java.util.*;

public class Scheduler {
	
	
	private final List <Process> processList;
	
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
		
	  Collections.sort(processList, Comparator.comparingInt(Process::getArrival));
	  int currentTime=0;
	   
	  for(Process process:processList)
	  {
		  if (currentTime< process.getArrival())
		  {
			  currentTime=process.getArrival();
		  }
		  
		  process.markReady(); //mark process as ready
		  
		  process.markRunning(currentTime);//transition to running state
		  
		  //update current time after process finish
		  currentTime+=process.getBurst();
		  process.markFinished(currentTime); // mark process as finished
	  }
	    
	    
	}
	
	public void runRoundRobin(int q)
	{
		processList.sort(Comparator.comparingInt(Process::getArrival));
		
		Queue<Process> ready = new ArrayDeque<>();
		
		int currentTime=0;
		
		int next=0;
		
		while (!ready.isEmpty() || next <processList.size()) {
			
			//enqueue all jobs that have arrived by current time
		     while(next<processList.size() &&
					processList.get(next).getArrival()<=currentTime)
			 {
				    Process p= processList.get(next++);
					p.markReady();
					ready.add(p);
			 }
			
		
		//if nothing ready go to next arrival
		if (ready.isEmpty())
		{
			    ready.add(processList.get(next));
			    currentTime = processList.get(next).getArrival();
			    next++;
			    continue;
		}
		
		//run one quantum
		Process p= ready.poll();
		p.markRunning(currentTime);
		
		int slice= Math.min(q, p.getRemaining());
		p.cpuTick(slice);//burn cpu
		currentTime+=slice; 
		
		while (next < processList.size()
			       && processList.get(next).getArrival() <= currentTime) {
			    Process nx = processList.get(next++);
			    nx.markReady();
			    ready.add(nx);
			}
		
		if(p.getRemaining()==0)
		{
			p.markFinished(currentTime); //process done
			p.setWaitingTime(p.getTurnAroundTime() - p.getBurst());
		}
		
		else
		{
			ready.add(p);//if p not finish at end of time slice send to back of queue
		}
		
	 }
		
		
}
	
	public void displayResults(String title)
	{
		System.out.println("\n=== "+ title+" ===");
		for (Process process: processList) {
			System.out.println(process.toString());
		}
	}
	

}
