package DiningPhilosopher;

public class Chopstick {
	private boolean isAvailable = true;
	private int id;

	public Chopstick(int id) {
		super();
		this.id = id;
	}

	public synchronized boolean isAvailable() {
		return isAvailable;
	}

	public synchronized void setAvailable(boolean isAvailable) {
		this.isAvailable = isAvailable;
	}
	
	public String toString(){
		return id + "";
	}
}
