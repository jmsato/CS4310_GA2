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

//	Each Ai is an integer chosen randomly from a uniform distribution
//	between 0 and some value k, where k is a simulation parameter
	public double getArrivalTime(){
		Random randomArrival = new Random();
		//TODO fix this
		//Not scaling standard deviation for now, only adding to the new mean
		double arrivalTime = randomArrival.nextGaussian()+k/2;
		return arrivalTime;

	}

//	Each Ti is an integer chosen randomly from a normal (Gaussian)
//	distribution with an average d and a standard deviation v, where d and v
//	are simulation parameters.
	public double getTotalCPUTime(){
		Random randomCPU=new Random();
		double totalCPUTime = randomCPU.nextGaussian()*v+d;
		return totalCPUTime;
	}

	public void createProcesses(int N){
		boolean active = true;
		processes = new Process[N];
		for(int i=0;i<N;i++){
			processes[i] = new Process(!active,  (int) getArrivalTime(), (int) getTotalCPUTime());
		}
		Comparator<Process> byArrivalTime = (p1, p2) -> p1.getArrivalTime()-p2.getArrivalTime();
		Arrays.sort(processes, byArrivalTime);
	}
	
	/**
	 * This method will run a simulation using the FIFO algorithm to schedule processes.
	 * @param args Unused.
   	 * @return int averageTurnaroundTime - the average time it takes for a process to be completed from its arrival time
	 */
	public int runSimulationFIFO(){
		int time=0; //time counter
		int indexInProcesses=0;
		int averageTurnaroundTime=0; 
		Comparator<Process> byArrival = (p1, p2) -> p1.getArrivalTime()-p2.getArrivalTime();
		PriorityQueue<Process> queue = new PriorityQueue<Process>(processes.length, byArrival); // for whatever reason, my compiler does not like 'N'
		
		Process pi=null;
		while(indexInProcesses<processes.length||!queue.isEmpty()||pi!=null){ 
			while(indexInProcesses<processes.length && processes[indexInProcesses].getArrivalTime()==time){//should exit loop if no processes with arrival time of t 
				queue.offer(processes[indexInProcesses]);
				indexInProcesses++;
			}
			
			if(pi==null&&queue.isEmpty()){//nothing to do in this case, just increment time
				time++;
				continue;
			}
			else if(!queue.isEmpty()&&pi==null){
				pi=queue.poll();
			}
			time++;//increment time after process has been chosen but BEFORE pi execution and calculations
			
			if(pi!=null){
				pi.setActive(true);
				pi.setRemainingCPUTime(pi.getRemainingCPUTime()-1);//decrement Ri
				if(pi.getRemainingCPUTime()<=0){
					pi.setActive(false);
					pi.setTurnaroundTime(time-pi.getArrivalTime());
					averageTurnaroundTime+=pi.getTurnaroundTime();
					pi=null;//end the execution of pi and choose new process
				}
			}
		}
		
		return averageTurnaroundTime/=this.processes.length;
		
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
