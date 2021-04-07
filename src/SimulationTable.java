
public class SimulationTable {

	public SimulationTable() {
		
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


}
