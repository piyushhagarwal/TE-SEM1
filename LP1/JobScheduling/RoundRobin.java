package LP1.JobScheduling;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

class Task {
    public String name;
    public int arrival_time, burst_time, waiting_time, completion_time, turnaround_time;

    public Task(String name, int arrival_time, int burst_time) {
        this.name = name;
        this.arrival_time = arrival_time;
        this.burst_time = burst_time;
    }
}

public class RoundRobin {

    public static void RRAlgo(ArrayList<Task> tasks, int quantum) {

        // Sort the tasks by arrival time
        int n = tasks.size();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n - i - 1; i++) {
                if (tasks.get(j).arrival_time > tasks.get(j + 1).arrival_time) {
                    Task temp = tasks.get(j);
                    tasks.set(j, tasks.get(j + 1));
                    tasks.set(j + 1, temp);
                }
            }
        }

        int totalWaitingTime = 0;
        int totalTurnArountTime = 0;
        int totalCompletionTime = 0;

        Queue<Task> readyQueue = new LinkedList<>();
        int currentTime = 0;

        for (Task task : tasks) {
            if (task.arrival_time == 0) {
                readyQueue.add(task);
            }
        }

        while (!readyQueue.isEmpty()) {
            Task currentTask = readyQueue.poll();

            if (currentTask.burst_time > quantum) {
                currentTask.burst_time -= quantum;
                currentTime += quantum;
            } else {
                currentTask.waiting_time = currentTime - currentTask.arrival_time;
                currentTime += currentTask.burst_time;
                currentTask.burst_time = 0;
                currentTask.completion_time = currentTime;
                currentTask.turnaround_time = currentTask.completion_time - currentTask.arrival_time;
                totalWaitingTime += currentTask.waiting_time;
                totalCompletionTime += currentTask.completion_time;
                totalTurnArountTime += currentTask.turnaround_time;
            }

            for (Task task : tasks) {
                if ((task.arrival_time <= currentTime) && (task.arrival_time > (currentTime - quantum))
                        && (task.burst_time != 0)) {
                    readyQueue.add(task);
                }
            }

            if (currentTask.burst_time != 0) {
                readyQueue.add(currentTask);
            }

        }

        // Print the results
        System.out.println("Task Name\tArrival Time\tBurst Time\tWaiting Time\tCompletion Time\tTurnaround Time");
        for (Task task : tasks) {
            System.out.println(task.name + "\t\t" + task.arrival_time + "\t\t" + task.burst_time + "\t\t"
                    + task.waiting_time + "\t\t" + task.completion_time + "\t\t" + task.turnaround_time);
        }

        System.out.println("Average Waiting Time: " + (float) totalWaitingTime / tasks.size());
        System.out.println("Average Turnaround Time: " + (float) totalTurnArountTime / tasks.size());
        System.out.println("Average Completion Time: " + (float) totalCompletionTime / tasks.size());

    }

    public static void main(String args[]) {
        ArrayList<Task> tasks = new ArrayList<>();
        tasks.add(new Task("T1", 0, 5));
        tasks.add(new Task("T2", 1, 3));
        tasks.add(new Task("T3", 2, 8));
        tasks.add(new Task("T4", 3, 6));
        tasks.add(new Task("T5", 4, 4));

        RRAlgo(tasks, 2);
    }
}
