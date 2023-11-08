package LP1.JobScheduling;

public class Task {
    public String name;
    public int arrival_time, burst_time, waiting_time, completion_time, turnaround_time, priority;

    public Task() {
        name = " ";
        arrival_time = 0;
        burst_time = 0;
        waiting_time = 0;
        completion_time = 0;
        turnaround_time = 0;
        priority = 0;
    }

    public Task(String name, int arrival_time, int burst_time, int priority) {
        this.name = name;
        this.arrival_time = arrival_time;
        this.burst_time = burst_time;
        this.priority = priority;
    }

    public String getName() {
        return name;
    }

    public int getArrivalTime() {
        return arrival_time;
    }

    public int getBurstTime() {
        return burst_time;
    }

    public int getPriority() {
        return priority;
    }

    public int getWaitingTime() {
        return waiting_time;
    }

    public int getCompletionTime() {
        return completion_time;
    }

    public int getTurnaroundTime() {
        return turnaround_time;
    }

    public void setWaitingTime(int waiting_time) {
        this.waiting_time = waiting_time;
    }

    public void setCompletionTime(int completion_time) {
        this.completion_time = completion_time;
    }

    public void setTurnaroundTime(int turnaround_time) {
        this.turnaround_time = turnaround_time;
    }

}
