import java.util.*;
import java.io.FileWriter;  

public class Test {

	public static void main(String[] args) {
		int[] numberOfProcesses = {50, 200, 300, 400, 500, 600, 700, 800, 900, 1000};
		int i = 0, runTests = 1000, k = 1000, d = 50, v = 12;

		try {
			List<String[]> rows = new ArrayList<String[]>();
			FileWriter csvWriter = new FileWriter("results.csv");
			csvWriter.append("FIFO");
			csvWriter.append(",");
			csvWriter.append("SJF");
			csvWriter.append(",");
			csvWriter.append("SRT");
			csvWriter.append("\n");

			/*Number of Processes Test Suite*/
			rows.add(new String[]{"Number of processes cases:"});

			//Test Case 1: k = 1000, d = 50, v = 12, n = numberOfProcesses[i]
			while(i < numberOfProcesses.length){

				rows.add(new String[]{"k = 1000, d = 50, v = 12, n = " + numberOfProcesses[i]});
				for(int times = 0; times < runTests; times++) {
					String[] r = new String[3];
					SimulationTable table = new SimulationTable(k, d, v, numberOfProcesses[i]);
					r[0] = Integer.toString(table.runSimulationFIFO());
					table.resetProcesses();
					r[1] = Integer.toString(table.runSimulationSJF());
					table.resetProcesses();
					r[2] = Integer.toString(table.runSimulationSRT());
					rows.add(r);
				}
				rows.add(new String[]{" "});
				i++;
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
