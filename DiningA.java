package DiningPhilosopher;
import java.util.ArrayList;

public class DiningA {
	private DiningTable diningTable;
	private ArrayList<Chopstick> chopstickList;
	private ArrayList<Philosopher> philosophers;
	
//	public static void main(String[] args) {
//		new DiningApp().start();
//	}


	public void start() {
		diningTable = new DiningTable();
		chopstickList = diningTable.getChopsticks();
		philosophers = diningTable.getDiners();
		addChopsticks();
		System.out.println(chopstickList);
		addDiners();
		System.out.println(philosophers);
		startDining();
	}

	private void startDining() {
		Thread diner1 = new Thread(philosophers.get(0));
		Thread diner2 = new Thread(philosophers.get(1));
		Thread diner3 = new Thread(philosophers.get(2));
		Thread diner4 = new Thread(philosophers.get(3));
		Thread diner5 = new Thread(philosophers.get(4));
		diner1.start();
		diner2.start();
		diner3.start();
		diner4.start();
		diner5.start();
	}

	private void addDiners() {
		int rightChopstick = 4;
		for(int i = 0; i < 5; ++i){
			if(i == 1){
				rightChopstick = 0;
			}else if (i == 2) {
				rightChopstick = 1;
			}else if (i == 3) {
				rightChopstick = 2;
			}else if (i == 4) {
				rightChopstick = 3;
			}
			philosophers.add(new Philosopher(i + 1, chopstickList.get(i), chopstickList.get(rightChopstick)));
		}
	}

	private void addChopsticks() {
		for(int i = 0; i < 5; ++i){
			chopstickList.add(new Chopstick(i + 1));
		}
	}

	public ArrayList<Philosopher> getPhilosophers() {
		return philosophers;
	}

	public void setPhilosophers(ArrayList<Philosopher> philosophers) {
		this.philosophers = philosophers;
	}

	public ArrayList<Chopstick> getChopstickList() {
		return chopstickList;
	}

	public void setChopstickList(ArrayList<Chopstick> chopstickList) {
		this.chopstickList = chopstickList;
	}
	
	
}
