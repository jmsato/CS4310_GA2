import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

public class Test {

	public static void main(String[] args) {
		int[] numberOfProcesses = {50, 200, 300};
		int i = 0, runTests = 1000, k = 1000, d = 50, v = 12;
		
		System.out.println("Running Test.java file...");
		
		//Create CSV writer:
		try {
			System.out.println("Creating results.csv file...");
			List<String[]> rows = new ArrayList<String[]>();
			FileWriter csvWriter = new FileWriter("customResults.csv");
			csvWriter.append("FIFO");
			csvWriter.append(",");
			csvWriter.append("SJF");
			csvWriter.append(",");
			csvWriter.append("SRT");
			csvWriter.append("\n");

			System.out.println("Running number of processes test suite...");
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
			
			System.out.println("Running arrival time interval test suite...");
			/*Arrival Time Interval Test Suite*/
			rows.add(new String[]{"Arrival time interval cases:"});
			
			//Test Case 1: k = 1000, d = 50, v = 12, n = 100
			rows.add(new String[]{"k = 1000, d = 50, v = 12, n = 100"});
			for(int times = 0; times < runTests; times++) {
				//Create a new row for each test run
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
			rows.add(new String[]{" "});
			
			System.out.println("Running mean total CPU time test suite...");
			/*Mean Total CPU Time Test Suite*/
			rows.add(new String[]{"Mean total CPU time cases:"});
			
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
			
			//Test Case 8: k = 1000, d = 25, v = 6, n = 100
			rows.add(new String[]{"k = 1000, d = 25, v = 6, n = 100"});
			for(int times = 0; times < runTests; times++) {
				String[] r = new String[3];
				SimulationTable case2 = new SimulationTable(1000, 25, 6, 100);
				r[0] = Integer.toString(case2.runSimulationFIFO());
				case2.resetProcesses();
				r[1] = Integer.toString(case2.runSimulationSJF());
				case2.resetProcesses();
				r[2] = Integer.toString(case2.runSimulationSRT());
				rows.add(r);
			}
			rows.add(new String[]{" "});
			
			//Test Case 9: k = 1000, d = 100, v = 25, n = 100
			rows.add(new String[]{"k = 1000, d = 100, v = 25, n = 100"});
			for(int times = 0; times < runTests; times++) {
				String[] r = new String[3];
				SimulationTable case3 = new SimulationTable(1000, 100, 25, 100);
				r[0] = Integer.toString(case3.runSimulationFIFO());
				case3.resetProcesses();
				r[1] = Integer.toString(case3.runSimulationSJF());
				case3.resetProcesses();
				r[2] = Integer.toString(case3.runSimulationSRT());
				rows.add(r);
			}
			rows.add(new String[]{" "});
			
			//Test Case 10: k = 1000, d = 75, v = 19, n = 100
			rows.add(new String[]{"k = 1000, d = 75, v = 19, n = 100"});
			for(int times = 0; times < runTests; times++) {
				String[] r = new String[3];
				SimulationTable case4 = new SimulationTable(1000, 75, 19, 100);
				r[0] = Integer.toString(case4.runSimulationFIFO());
				case4.resetProcesses();
				r[1] = Integer.toString(case4.runSimulationSJF());
				case4.resetProcesses();
				r[2] = Integer.toString(case4.runSimulationSRT());
				rows.add(r);
			}


			
			// rows.add(new String[]{" "});
			// System.out.println("Running custom CPU time test suite...");
			// /*Custom CPU Time Test Suite*/
			// rows.add(new String[]{"Custom Hypothesis Test Suite:"});
			// /*for(int times = 0; times < 1; times++) {
			// 	String[] r = new String[3];
			// 	SimulationTable case5 = new SimulationTable(200, 75, 19, 20, 2);
			// 	r[0] = Integer.toString(case5.runSimulationFIFO());
			// 	case5.resetProcesses();
			// 	r[1] = Integer.toString(case5.runSimulationSJF());
			// 	case5.resetProcesses();
			// 	r[2] = Integer.toString(case5.runSimulationSRT());
			// 	rows.add(r);
			// }*/
			// String[] r = new String[3];
			// SimulationTable case5 = new SimulationTable(30, 50, 10, 50, 2); //k, d, v, n
			// 	r[0] = Integer.toString(case5.runSimulationFIFO());
			// 	String[] p=new String[50];
			// 	Process[] temp=case5.getProcesses();
			// 	for(int index=0;index<50;index++){
			// 		p[index]=Integer.toString(index)+": "+temp[index].toString();
			// 	}
			// 	rows.add(p);

			// 	case5.resetProcesses();
			// 	r[1] = Integer.toString(case5.runSimulationSJF());
			// 	case5.resetProcesses();
			// 	temp=case5.getProcesses();
			// 	for(int index=0;index<50;index++){
			// 		p[index]=Integer.toString(index)+": "+temp[index].toString();
			// 	}
			// 	rows.add(p);
			// 	r[2] = Integer.toString(case5.runSimulationSRT());
				

			// 	temp=case5.getProcesses();
			// 	for(int index=0;index<50;index++){
			// 		p[index]=Integer.toString(index)+": "+temp[index].toString();
			// 	}
			// 	rows.add(p);
			// 	rows.add(r);
				


			
			System.out.println("Writing to results.csv file...");
			//Write to the CSV file
			for (String[] rowData : rows) {
			    csvWriter.append(String.join(",", rowData));
			    csvWriter.append("\n");
			}
			//Close CSV writer
			csvWriter.flush();
			csvWriter.close();
			System.out.println("Done!");
		} catch(Exception e) {
			System.out.println("Something failed");
			e.printStackTrace();
		}
	}
}