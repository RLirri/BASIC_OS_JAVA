package DiningPhilosopher;

import java.util.ArrayList;

public class Philosopher implements Runnable{
	private boolean isEating;
	private boolean hasLeftChopstick;
	private boolean hasRightChopstick;
	private int amountEaten;
	private boolean isFinished;
	private boolean isSnoozing;
	private String name; 
	private ArrayList<Chopstick> chopsticks = new ArrayList<>();
	
	public Philosopher(int name, Chopstick leftChopstick, Chopstick rightChopstick) {
		super();
		this.name = "Philosopher: " + name;
		chopsticks.add(leftChopstick);
		chopsticks.add(rightChopstick);
	}

	public synchronized boolean isSnoozing() {
		return isSnoozing;
	}

	public synchronized void setSnoozing(boolean isSnoozing) {
		this.isSnoozing = isSnoozing;
	}

	public synchronized boolean isEating() {
		return isEating;
	}

	public synchronized void setEating(boolean isEating) {
		this.isEating = isEating;
	}

	public synchronized boolean hasLeftChopstick() {
		return hasLeftChopstick;
	}

	public synchronized boolean isFinished() {
		return isFinished;
	}

	public synchronized void setFinished(boolean isFinished) {
		this.isFinished = isFinished;
	}

	public synchronized void hasLeftChopstick(boolean hasLeftChopstick) {
		this.hasLeftChopstick = hasLeftChopstick;
	}
	
	public synchronized boolean hasRightChopstick() {
		return hasRightChopstick;
	}

	public synchronized void hasRightChopstick(boolean hasRightChopstick) {
		this.hasRightChopstick = hasRightChopstick;
	}

	public int getAmountEaten() {
		return amountEaten;
	}

	public void setAmountEaten(int amountEaten) {
		this.amountEaten = amountEaten;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public void run() {
		while (amountEaten < 51) {
			checkIfLeftChopstickAvailable();
			if(hasLeftChopstick)checkIfRightChopstickAvailable();
			if (hasLeftChopstick && hasRightChopstick) {
				isEating = true;
				startEating();
			} else{
				try {
					Thread.sleep(500);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
		isFinished = true;
	}

	private void startEating() {
		for(int i = 0; i < 10; ++i){
			++amountEaten;
			System.out.println(this.name + " is now eating, amount is now: " + amountEaten);
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		for(Chopstick fork : chopsticks){
			fork.setAvailable(true);
		}
		isEating = false;
		try {
			isSnoozing = true;
			Thread.sleep(5000);
			isSnoozing = false;
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	private void checkIfRightChopstickAvailable() {
		System.out.println(this.name + " checking if right fork available");
		if(chopsticks.get(1).isAvailable()){
			hasRightChopstick = true;
			chopsticks.get(1).setAvailable(false);
			System.out.println(this.name + "right fork is available & now setting it to: " + chopsticks.get(1).isAvailable());
			return;
		}
		System.out.println(this.name + "right fork was not available & status is: " + chopsticks.get(1).isAvailable());
		chopsticks.get(0).setAvailable(true);
		hasRightChopstick = false;
		hasLeftChopstick = false;
	}

	private void checkIfLeftChopstickAvailable() {
		System.out.println(this.name + " checking if left fork available");
		if(chopsticks.get(0).isAvailable()){
			hasLeftChopstick = true;
			chopsticks.get(0).setAvailable(false);
			System.out.println(this.name + ": left fork is available & now setting it to: " + chopsticks.get(0).isAvailable());
			return;
		}
		System.out.println(this.name + ": left fork was not available & status is: " + chopsticks.get(0).isAvailable());
		hasLeftChopstick = false;
	}
	
	public String toString(){
		return name + " has chopsticks " + chopsticks;
	}
}
