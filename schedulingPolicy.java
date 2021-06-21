
import java.util.*;

public class schedulingPolicy {

    private Job[] jobsRemaining, jobs;
    private Queue<Job> readyQueue;
    private Job currentJob;
    private int numOfJobs, time, timeQuantum;

    public schedulingPolicy(int numOfJob, int[] burstTimeArray, int time_Quantum) {
        numOfJobs = numOfJob;
        timeQuantum = time_Quantum;
        jobs = new Job[numOfJobs];

        for (int i = 0; i < numOfJobs; i++)
            jobs[i] = new Job(i, burstTimeArray[i], i);
    }
    public void scheduleReset(){
        time = 0;
        jobsRemaining = Arrays.copyOf(jobs, numOfJobs);
    }
    public boolean jobArrayIsEmpty(){
        for (Job job : jobsRemaining){
            if (job != null)
                return false;
        }
        return true;
    }
    public void roundRobin(){
        scheduleReset();
        readyQueue = new LinkedList<>();
        while (!(jobArrayIsEmpty() && readyQueue.isEmpty() && currentJob == null)) {
            jobsIntoReadyQueue();

            if (currentJob == null)
                currentJob = readyQueue.poll();

            display();

            if (currentJob.getExecutionTime() == currentJob.getBurstTime()) {
                currentJob.setCompletionTime(time);
                time++;
                jobsIntoReadyQueue();
                currentJob = readyQueue.poll();
                if (currentJob != null)
                    currentJob.setExecutionTime(currentJob.getExecutionTime() == 0 ?
                            1 : currentJob.getExecutionTime());
            }
            else if (currentJob.getExecutionTime() % timeQuantum == 0){
                currentJob.setExecutionTime(currentJob.getExecutionTime() + 1);
                time++;
                jobsIntoReadyQueue();
                readyQueue.add(currentJob);
                currentJob = readyQueue.poll();
                if (currentJob != null)
                    currentJob.setExecutionTime(currentJob.getExecutionTime() == 0 ?
                            1 : currentJob.getExecutionTime());
            }
            else {
                currentJob.setExecutionTime(currentJob.getExecutionTime() + 1);
                time++;
            }
        }
        displayResult();

    }

    public void jobsIntoReadyQueue() {
        if (!jobArrayIsEmpty()){
            for (int i = 0; i < numOfJobs; i++){
                if (jobsRemaining[i] == null)
                    continue;
                if (jobsRemaining[i].getArrivalTime() == time) {
                    readyQueue.add(jobsRemaining[i]);
                    jobsRemaining[i] = null;
                }
            }
        }
    }

    public void shortestJobFirst() {
        scheduleReset();
        readyQueue = new PriorityQueue<>(Job::compareTo);
        while (!(jobArrayIsEmpty() && readyQueue.isEmpty() && currentJob == null)) {
            jobsIntoReadyQueue();

            if (currentJob == null)
                currentJob = readyQueue.poll();

            display();

            if (currentJob.getExecutionTime() == currentJob.getBurstTime()) {
                currentJob.setCompletionTime(time);
                time++;
                jobsIntoReadyQueue();
                currentJob = readyQueue.poll();

                if (currentJob != null)
                    currentJob.setExecutionTime(1);
            }
            else {
                currentJob.setExecutionTime(currentJob.getExecutionTime() + 1);
                time++;
            }
        }
        displayResult();
    }
    public void display(){
        System.out.println();
        System.out.println("Time: " + time + "ms");
        System.out.println("Job remaining: " + Arrays.toString(jobsRemaining));
        System.out.println("READY Queue: " + readyQueue);
        System.out.println("Current executing job: " + currentJob);
    }
    public void displayResult(){
        double turnaroundTimeSum = 0, waitingTimeSum = 0;
        for (Job job : jobs) {
            System.out.println(job.result());
            turnaroundTimeSum += job.getTurnaroundTime();
            waitingTimeSum += job.getWaitingTime();
        }
        System.out.printf("\nAverage Turnaround Time (Completion Time - Arrival Time): %.2fms",turnaroundTimeSum/numOfJobs);
        System.out.printf("\nAverage waiting time (Turnaround Time - Burst Time): %.2fms\n", waitingTimeSum/numOfJobs);

    }
}

