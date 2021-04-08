import java.util.*;

public class SimulationTable {

	private int k;
	private int d;
	private int v;
	private Process[] processes;
	private int N; // Number of processes
	private Random random;

	public SimulationTable(int k, int d, int v, int N) {
		this.k = k;
		this.d = d;
		this.v = v;
		random = new Random();
		createProcesses(N);
	}
	
	// Do math for random process arrival times and total CPU times


//	Each Ai is an integer chosen randomly from a uniform distribution
//	between 0 and some value k, where k is a simulation parameter
	public double getArrivalTime(){
		double arrivalTime = random.nextGaussian();
		return arrivalTime;

	}

//	Each Ti is an integer chosen randomly from a normal (Gaussian)
//	distribution with an average d and a standard deviation v, where d and v
//	are simulation parameters.
	public double getTotalCPUTime(){
		double totalCPUTime = random.nextGaussian();
		return totalCPUTime;
	}

	public void createProcesses(int N){
		boolean active = true;
		processes = new Process[N];
		for(int i=0;i<N;i++){
			processes[i] = new Process(!active,  (int) getArrivalTime(), (int) getTotalCPUTime());
		}

	}
	
	/**
	 * This method will run a simulation using the FIFO algorithm to schedule processes.
	 * @param args Unused.
   	 * @return Nothing.
	 */
	//TODO
	// finish method: push processes to queue at arrival times; 
	// correct variable names Ri, pi, etc.;
	//finish general integration with simulationTable constructor
	public void runSimulationFIFO(){
		int t=0; //time counter
		Comparator<Process> byArrival = (p1, p2) -> p1.getArrivalTime()-p2.getArrivalTime();
		PriorityQueue<Process> queue = new PriorityQueue<Process>(processes.length, byArrival); // for whatever reason, my compiler does not like 'N'
		queue.addAll(Arrays.asList(processes));
		//TODO
		//remove once finished testing
		System.out.println("PriorityQueue");
		System.out.println(queue.isEmpty());
		while(!queue.isEmpty()){
			System.out.print(queue.poll().toString());
		}

		/*
		Process pi;
		while(Ri>0){ //find a way to get remaining time of processes
			t++; //advancing t while no process is active...

			//choosing active process pi
			pi=queue.poll() // assuming we have some sort of queue or arraylist to pop the process off of
			
			pi.setRemainingCPUTime(pi.getRemainingCPUTime()-1);//decrement Ri
			if(pi.getRemainingCPUTime()==0){
				pi.setActive(false);
				pi.setTurnaroundTime(t-pi.getArrivalTime());
			}
		}
		//compute average turnaround time
		int averageTurnaroundTime=0;
		foreach(Process p: this.processArray){
			averageTurnaroundTime+=p.getTurnaroundTime();
		}
		averageTurnaroundTime/=this.processArray.length;
		*/
	}

	public void runSimulationSJF(){
		int t=0; //time counter
		PriorityQueue<Process> queue = new PriorityQueue<Process>(processes.length, 
			Comparator.comparing(Process::getRemainingCPUTime)
					.thenComparing(Process::getArrivalTime)); //using :: to do method reference and creating chain comparators
		
		
		//TODO
		//remove once finish testing
		queue.addAll(Arrays.asList(processes));
		System.out.println(queue.isEmpty());
		while(!queue.isEmpty()){
			System.out.print(queue.poll().toString());
		}
	}
	public void runSimulationSRT(){
		int t=0; //time counter
		PriorityQueue<Process> queue = new PriorityQueue<Process>(processes.length, 
			Comparator.comparing(Process::getRemainingCPUTime)
					.thenComparing(Process::getArrivalTime)); //using :: to do method reference and creating chain comparators
		//TODO must add the processes into queue
	}

}
