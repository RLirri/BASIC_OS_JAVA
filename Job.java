
public class Job implements Comparable<Job>{

    private int id, burstTime, arrivalTime, completionTime, turnaroundTime, waitingTime, executionTime = 0;

    public Job(int id, int burstTime, int arrivalTime) {
        this.id = id;
        this.burstTime = burstTime;
        this.arrivalTime = arrivalTime;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getBurstTime() {
        return burstTime;
    }

    public void setBurstTime(int burstTime) {
        this.burstTime = burstTime;
    }

    public int getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(int arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public int getCompletionTime() {
        return completionTime;
    }

    public void setCompletionTime(int completionTime) {
        this.completionTime = completionTime;
    }

    public int getTurnaroundTime() {
        return turnaroundTime;
    }

    public void setTurnaroundTime() {
        this.turnaroundTime = completionTime - arrivalTime;
    }

    public int getWaitingTime() {
        return waitingTime;
    }

    public void setWaitingTime() {
        this.waitingTime = turnaroundTime - burstTime;
    }

    public int getExecutionTime() {
        return executionTime;
    }

    public void setExecutionTime(int executionTime) {
        this.executionTime = executionTime;
    }

    @Override
    public int compareTo(Job job) {
        int index = Integer.compare(this.burstTime, job.burstTime);
        if (index == 0)
            return Integer.compare(this.arrivalTime, job.arrivalTime);
        return index;
    }

    @Override
    public String toString() {
        return "Job " + id +
                "{Burst Time: " + burstTime + "ms" +
                ", Arrival Time: " + arrivalTime + "ms" +
                ", Execution Time: " + executionTime + "ms" +
                '}';
    }

    public String result() {
        setTurnaroundTime();
        setWaitingTime();
        return "\nJob " + id +
                "\nCompletion Time: " + completionTime + "ms" +
                "\nArrival Time: " + arrivalTime + "ms" +
                "\nTurnaround Time: " + getTurnaroundTime() + "ms" +
                "\nWaiting Time: " + getWaitingTime() + "ms";
    }
}
