package DiningPhilosopher;

import java.util.ArrayList;

public class DiningTable {
	private ArrayList<Philosopher> diners = new ArrayList<>();
	private ArrayList<Chopstick> chopsticks = new ArrayList<>();
	
	public ArrayList<Philosopher> getDiners() {
		return diners;
	}
	public void setDiners(ArrayList<Philosopher> diners) {
		this.diners = diners;
	}
	public ArrayList<Chopstick> getChopsticks() {
		return chopsticks;
	}
	
	public void setChopsticks(ArrayList<Chopstick> chopsticks) {
		this.chopsticks = chopsticks;
	}
	
	
}
