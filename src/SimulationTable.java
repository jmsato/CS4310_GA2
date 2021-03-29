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
	
	
	
	public void runSimulationFIFO(){

	}
	public void runSimulationSJF(){

	}
	public void runSimulationSRT(){

	}

}
