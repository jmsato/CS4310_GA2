import java.util.*;

public class SimulationTable {

	private int k; //upper bound for arrival times
	private int d; //average total CPU time
	private int v; //standard deviation (should be a percentage of d) 
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
	public int getArrivalTime(){
		Random randomArrival = new Random();
		int arrivalTime = randomArrival.nextInt(k+1);
		return arrivalTime;
	}

//	Each Ti is an integer chosen randomly from a normal (Gaussian)
//	distribution with an average d and a standard deviation v, where d and v
//	are simulation parameters.
	public int getTotalCPUTime(){
		Random randomCPU=new Random();
		int totalCPUTime = (int)randomCPU.nextGaussian()*v+d;
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
	
	/**
	 * This method will run a simulation using the FIFO algorithm to schedule processes.
	 * @param args Unused.
   	 * @return int averageTurnaroundTime - the average time it takes for a process to be completed from its arrival time
	 */
	public int runSimulationFIFO(){
		int time=0;
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
	
	/**
	 * This method will run a simulation using the SJF algorithm to schedule processes.
	 * @param args Unused.
   	 * @return int averageTurnaroundTime - the average time it takes for a process to be completed from its arrival time
	 */
	public double runSimulationSJF(){
		int time=0; 
		int indexInProcesses=0;
		int averageTurnaroundTime=0; 
		PriorityQueue<Process> queue = new PriorityQueue<Process>(processes.length, 
			Comparator.comparing(Process::getRemainingCPUTime)
					.thenComparing(Process::getArrivalTime)); //using :: to do method reference and creating chain comparators

		Process pi=null;
		while(indexInProcesses<processes.length||!queue.isEmpty()||pi!=null){ 
			while(indexInProcesses<processes.length && processes[indexInProcesses].getArrivalTime()==time){//should exit loop if no processes with arrival time of t 
				queue.offer(processes[indexInProcesses]);
				indexInProcesses++;
			}
			if(pi==null&&queue.isEmpty()){
				time++;
				continue;
			}
			else if(!queue.isEmpty()&&pi==null){
				pi=queue.poll();
			}
			time++;
			if(pi!=null){
				pi.setActive(true);
				pi.setRemainingCPUTime(pi.getRemainingCPUTime()-1);
				if(pi.getRemainingCPUTime()<=0){
					pi.setActive(false);
					pi.setTurnaroundTime(time-pi.getArrivalTime());
					averageTurnaroundTime+=pi.getTurnaroundTime();
					pi=null;//end the execution of pi and choose new process, can't just break
				}
			}
		}
		return averageTurnaroundTime/=this.processes.length;
	}
	
	/**
	 * This method will run a simulation using the SRT algorithm to schedule processes.
	 * @param args Unused.
   	 * @return int averageTurnaroundTime - the average time it takes for a process to be completed from its arrival time
	 */
	public int runSimulationSRT(){
		int time=0;
		int indexInProcesses=0;
		int averageTurnaroundTime=0; 
		PriorityQueue<Process> queue = new PriorityQueue<Process>(processes.length, 
			Comparator.comparing(Process::getRemainingCPUTime)
					.thenComparing(Process::getArrivalTime)); //using :: to do method reference and creating chain comparators

		Process pi=null;
		while(indexInProcesses<processes.length||!queue.isEmpty()||pi!=null){
			
			while(indexInProcesses<processes.length && processes[indexInProcesses].getArrivalTime()==time){//should exit loop if no processes with arrival time of t 
				queue.offer(processes[indexInProcesses]);
				indexInProcesses++;
			}
			if(pi==null&&queue.isEmpty()){
				time++;
				continue;
			}
			else if(!queue.isEmpty()&&pi==null){
				pi=queue.poll();
			}
			else if(!queue.isEmpty()&&pi!=null&&pi.getRemainingCPUTime()>queue.peek().getRemainingCPUTime()){//adding third case, to interrupt if next process has shorter remaining time
				pi.setActive(false);
				queue.offer(pi);//add pi back onto the queue
				pi=queue.poll();
			}
			time++;

			if(pi!=null){
				pi.setActive(true);
				pi.setRemainingCPUTime(pi.getRemainingCPUTime()-1);
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

}
