
import java.util.Scanner;

    class Banker {
	
/**The banker algorithm needs to set up four data structures, namely
*(1) the maximum demand of all processes for resources,
*(2) resource allocation in the system,
*(3) the resources needed by all processes,
*(4) describe the available resources in the system,
*/
	
	int m; 					
	int n; 					
	int[][] max; 			
	int[][] allocation;	    
	int[][] need; 			
	int[] available;		
	Scanner sc = new Scanner(System.in);

	public BankerAlgorithm1() {
		System.out.println("Please input the number of processing m:");
		m = sc.nextInt();
		System.out.println("please input the number of resource n:");
		n = sc.nextInt();
		max = new int[m][n];
		allocation = new int[m][n];
		need = new int[m][n];
		available = new int[n];

		System.out.println("Please enter a" + m + "rows" + n + "columns (the maximum requirements for each process)");
		for (int i = 0; i < max.length; i++) { 
			System.out.println("please enter the max process of p(" + i + ")");
			for (int j = 0; j < max[i].length; j++) {
				max[i][j] = sc.nextInt();
			}
		}
		System.out.println("please enter a" + m + "rows" + n + "columns (the amount of occupancy for each process )");
		for (int i = 0; i < allocation.length; i++) { 
			System.out.println("please enter the allocation process of p(" + i + ")");
			for (int j = 0; j < allocation[i].length; j++) {
				allocation[i][j] = sc.nextInt();
			}
		}
		for (int i = 0; i < need.length; i++) { 
			for (int j = 0; j < need[i].length; j++) {
				need[i][j] = max[i][j] - allocation[i][j];
			}
		}
		System.out.println("Please enter the number of resources available"); 
		for (int i = 0; i < n; i++) {
			available[i] = sc.nextInt();
		}
		System.out.println("The table is:");
		print();
	}

	public void print() {
		System.out.println("------------------------------------------");
		System.out.println("\tMax\tAllocation\tNeed\tAvailable");
		System.out.println("\tA B C\tA B C\t\tA B C\tA B C");
		for (int i = 0; i < m; i++) {
			System.out.print("P(" + i + "): "+"  ");
			for (int j = 0; j < n; j++) {
				System.out.print(max[i][j] + " ");
			}
			System.out.print("\t");
			for (int j = 0; j < n; j++) {
				System.out.print(allocation[i][j] + " ");
			}
			System.out.print("\t\t");
			for (int j = 0; j < n; j++) {
				System.out.print(need[i][j] + " ");
			}
			System.out.print("\t");
			if (i == 0) {
				for (int j = 0; j < n; j++) {
					System.out.print(available[j] + " ");
				}
			}
			System.out.println();
		}
		System.out.println("------------------------------------------");
	}

	
	public boolean checkSecurity() {
		int[] work = new int[n];			
		boolean[] finish = new boolean[m];  
		
		int numberResource = 0;				
		int numberProcess = 0;				
		int countAllocation = 0;			
		int countProcess = 0;				
		int[] p = new int[m];				

		
		for (int i = 0; i < n; i++) {
			work[i] = available[i];
		}
	
		for (int i = 0; i < m; i++) { 
			finish[i] = false;
		}

		while (numberProcess < m) {
			for (int i = 0; i < m; i++) {
				if (finish[i] == false) {
					for (int j = 0; j < n; j++) {
						if (need[i][j] <= work[j]) {
							numberResource++;
						}
					}
					if (numberResource == n) {
						for (int j = 0; j < n; j++) {
							work[j] = work[j] + allocation[i][j];
						}
						finish[i] = true;
						p[countAllocation++] = i;
					}
				}
				numberResource = 0;
			}
			numberProcess++;
		}

		
		for (int i = 0; i < m; i++) {
			if (finish[i] == true) {
				countProcess++;
			}
		}
		if (countProcess == m) {
			System.out.println("There is a security sequence, and the security sequence is:");
			for (int i = 0; i < m; i++) {
				if (i != m - 1) {
					System.out.print("P" + p[i] + "-->");
				} else {
					System.out.println("P" + p[i]);
				}
			}
			System.out.println("----------------------------------------------------");
			return true;
		} else {
			System.out.println("There is no sercurity sequence , and the situation is unsafe !");
			return false;
		}
	}
	
	public void checkRequest() {
		int process = 0;		   			
		int countAllocation = 0;			
		boolean flag = true;				
		System.out.println("Please enter the number of process (p0~p)"+(m-1)+":" );
		while (flag) {
			process = sc.nextInt();
			if (process >= m) {
				System.out.println("The number of processes is overload, Please enter again!");
			} else {
				flag = false;

			}
		}
		System.out.println("p"+process+"send a requst ");
		int[] request = new int[n];
		System.out.println("Please enter the resource requested:");
		for (int i=0;i<n;i++) {
			request[i] = sc.nextInt();
		}
		for (int i=0;i<n;i++) {
			if (request[i] <= need[process][i] && request[i] <= available[i]) {
				countAllocation++;
			}
		}

		if (countAllocation == n) {
			for (int j=0;j<n;j++) {
				allocation[process][j] += request[j];  
				need[process][j] -= request[j];
				available[j] -= request[j];
			}
			
			System.out.println("Trial distribution is as follow :");
			print();
			System.out.println("Check the security...");
			flag = checkSecurity();
			if (flag==false) {  
				for (int j=0;j<n;j++) {
					allocation[process][j] -= request[j];  
					need[process][j] += request[j];
					available[j] += request[j];
				}
			}
		} else {
			System.out.println("Cannot find the safety sequence, so can't distribution...");
		}
	}
public static void main(String[] args) {
       
		BankerAlgorithm1 bankerAlgorithm = new BankerAlgorithm1();
		boolean flag = true;
		while (flag) {
			System.out.println("1.Check the safety sequence");
			System.out.println("2.Trial distribution");
			System.out.println("3.Exit");
			System.out.println("----------------------------------------------------------");
			System.out.println("Please enter your choice:");
			Scanner sc = new Scanner(System.in);
			int count = sc.nextInt();
			switch (count) {
			case 1:
				bankerAlgorithm.checkSecurity();
				break;
			case 2:
				bankerAlgorithm.checkRequest();
				break;
			case 3:
				System.out.println("Thank you !!!");
				flag = false;
				sc.close();
				break;
			}			
		}


    }
}
