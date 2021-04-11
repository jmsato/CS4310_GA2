import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

public class Test {

	public static void main(String[] args) {
		int runTests = 1000;
		
		//Create CSV writer:
		try {
			List<String[]> rows = new ArrayList<String[]>();
			FileWriter csvWriter = new FileWriter("results.csv");
			csvWriter.append("FIFO");
			csvWriter.append(",");
			csvWriter.append("SJF");
			csvWriter.append(",");
			csvWriter.append("SRT");
			csvWriter.append("\n");
			
			/*Arrival Time Interval Test Suite*/
			rows.add(new String[]{"Arrival time interval cases:"});
			//Test Case 1: k = 1000, d = 50, v = 12, n = 100
			rows.add(new String[]{"k = 1000, d = 50, v = 12, n = 100"});
			for(int times = 0; times < runTests; times++) {
				String[] r = new String[3];
				SimulationTable case1 = new SimulationTable(1000, 50, 12, 100);
				r[0] = Integer.toString(case1.runSimulationFIFO());
				case1.resetProcesses();
				r[1] = Integer.toString(case1.runSimulationSJF());
				case1.resetProcesses();
				r[2] = Integer.toString(case1.runSimulationSRT());
				rows.add(r);
			}
			rows.add(new String[]{" "});
			
			//Test Case 2: k = 500, d = 50, v = 12, n = 100
			rows.add(new String[]{"k = 500, d = 50, v = 12, n = 100"});
			for(int times = 0; times < runTests; times++) {
				String[] r = new String[3];
				SimulationTable case2 = new SimulationTable(500, 50, 12, 100);
				r[0] = Integer.toString(case2.runSimulationFIFO());
				case2.resetProcesses();
				r[1] = Integer.toString(case2.runSimulationSJF());
				case2.resetProcesses();
				r[2] = Integer.toString(case2.runSimulationSRT());
				rows.add(r);
			}
			rows.add(new String[]{" "});
			
			//Test Case 3: k = 2500, d = 50, v = 12, n = 100
			rows.add(new String[]{"k = 2500, d = 50, v = 12, n = 100"});
			for(int times = 0; times < runTests; times++) {
				String[] r = new String[3];
				SimulationTable case3 = new SimulationTable(2500, 50, 12, 100);
				r[0] = Integer.toString(case3.runSimulationFIFO());
				case3.resetProcesses();
				r[1] = Integer.toString(case3.runSimulationSJF());
				case3.resetProcesses();
				r[2] = Integer.toString(case3.runSimulationSRT());
				rows.add(r);
			}
			rows.add(new String[]{" "});
			
			//Test Case 4: k = 2000, d = 50, v = 12, n = 100
			rows.add(new String[]{"k = 2000, d = 50, v = 12, n = 100"});
			for(int times = 0; times < runTests; times++) {
				String[] r = new String[3];
				SimulationTable case4 = new SimulationTable(2000, 50, 12, 100);
				r[0] = Integer.toString(case4.runSimulationFIFO());
				case4.resetProcesses();
				r[1] = Integer.toString(case4.runSimulationSJF());
				case4.resetProcesses();
				r[2] = Integer.toString(case4.runSimulationSRT());
				rows.add(r);
			}
			
			//Write to the CSV file
			for (String[] rowData : rows) {
			    csvWriter.append(String.join(",", rowData));
			    csvWriter.append("\n");
			}
			
			//Close CSV writer
			csvWriter.flush();
			csvWriter.close();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}