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
		Comparator<Process> byArrivalTime = (p1, p2) -> ((Integer)p1.getArrivalTime()).compareTo(((Integer)p2.getArrivalTime()));
		processes = new Process[N];
		for(int i=0;i<N;i++){
			int cpuTime = (int) getTotalCPUTime();
			Process p = new Process(!active, (int) getArrivalTime(), cpuTime);
			processes[i] = p;
		}
		Arrays.sort(processes, byArrivalTime);
	}
	
	public void printProcesses() {
		System.out.println("Testing order of arrival times: ");
		for(Process process: processes) {
			System.out.println(process.getArrivalTime());
		}
	}
	
	public void runSimulationFIFO(){

	}
	
	public double runSimulationSJF(){
		int time = 0;
		int totalTT = 0;
		//Creates ready list queue of all the processes
		PriorityQueue<Process> queue = new PriorityQueue<Process>(processes.length,
				Comparator.comparing(Process::getRemainingCPUTime).thenComparing(Process::getArrivalTime));
		queue.addAll(Arrays.asList(processes));
		//Get the first running process
		Process running = queue.poll();
		//Run simulation
		while(running != null) {
			//Figure out if the process is running
			if(time >= running.getArrivalTime()) {
				running.setActive(true);
			}
			//Adjust remaining CPU time
			if(running.isActive()) {
				running.setRemainingCPUTime(running.getRemainingCPUTime() - 1);
			}
			//Get the next process to run if the current process is done running and set turnaround time
			if(running.getTotalCPUTime() == 0) {
				running.setActive(false);
				int turnaroundTime = time - running.getArrivalTime();
				running.setTurnaroundTime(turnaroundTime);
				totalTT += turnaroundTime;
				running = queue.poll();
			}
		}
		//Computer and return average turnaround time
		return totalTT/N;
	}
	
	public void runSimulationSRT(){

	}

}
