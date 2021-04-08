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
	
	/**
	 * This method will run a simulation using the FIFO algorithm to schedule processes.
	 * @param args Unused.
   	 * @return Nothing.
	 */
	//TODO
	//very rough draft, just trying to get started here
	 public void runSimulationFIFO(){
		int t=0; //time counter
		Process pi;
		while(Ri>0){ //find a way to get remaining time of processes
			t++; //advancing t while no process is active...

			//choosing active process pi
			pi=this.pop() // assuming we have some sort of queue or arraylist to pop the process off of
			
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
	
	
	
	public void runSimulationFIFO(){

	}
	public void runSimulationSJF(){

	}
	public void runSimulationSRT(){

	}

}
