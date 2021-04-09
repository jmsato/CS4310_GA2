public class Process{

	private boolean active;
	private int arrivalTime;
	private int totalCPUTime;
	private int remainingCPUTime;
	private int turnaroundTime;
	
	public Process(boolean active, int arrivalTime, int totalCPUTime) {
		this.active = active;
		this.arrivalTime = arrivalTime;
		this.totalCPUTime = totalCPUTime;
		this.remainingCPUTime = totalCPUTime;
		this.turnaroundTime = 0;
	}
	
	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}
	
	public int getArrivalTime() {
		return arrivalTime;
	}

	public void setArrivalTime(int arrivalTime) {
		this.arrivalTime = arrivalTime;
	}

	public int getTotalCPUTime() {
		return totalCPUTime;
	}

	public void setTotalCPUTime(int totalCPUTime) {
		this.totalCPUTime = totalCPUTime;
	}

	public int getRemainingCPUTime() {
		return remainingCPUTime;
	}

	public void setRemainingCPUTime(int remainingCPUTime) {
		this.remainingCPUTime = remainingCPUTime;
	}

	public int getTurnaroundTime() {
		return turnaroundTime;
	}

	public void setTurnaroundTime(int turnaroundTime) {
		this.turnaroundTime = turnaroundTime;
	}

	public String toString(){
		return "Process [ Active: " + this.active +
		", Arrival Time: " + this.arrivalTime +
		", Total CPU Time: " + this.totalCPUTime+
		", Remaining CPU Time: " +this.remainingCPUTime+
		", Turnaround Time: " + turnaroundTime + " ]\n";
	}

}
