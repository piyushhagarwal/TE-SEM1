package LP1.JobScheduling;

import java.util.ArrayList;
import java.util.Scanner;

class Task {
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
}

public class Priority {
    public Task[] tasks;

    public Task HighestPriority(ArrayList<Task> readyQueue) {
        int highestPriorityIndex = 0;
        for (int i = 1; i < readyQueue.size(); i++) {
            if (readyQueue.get(i).priority > readyQueue.get(highestPriorityIndex).priority) {
                highestPriorityIndex = i;
            }
        }
        return readyQueue.get(highestPriorityIndex);
    }

    public void PriorityAlgo(Task tasks[]) {
        int totalWaitingTime = 0;
        int totalTurnaroundTime = 0;
        int totalCompletionTime = 0;
        ArrayList<Task> readyQueue = new ArrayList<>();
        ArrayList<Task> alreadyAddedTasks = new ArrayList<>();
        int completedTasks = 0;
        int currentTime = 0;
        while (completedTasks != tasks.length) {
            for (int i = 0; i < tasks.length; i++) {
                if ((tasks[i].arrival_time <= currentTime) && (alreadyAddedTasks.contains(tasks[i]) == false)) {
                    readyQueue.add(tasks[i]);
                    alreadyAddedTasks.add(tasks[i]);
                }
            }

            Task highesPriorityTask = HighestPriority(readyQueue);

            int waitingTime = currentTime - highesPriorityTask.arrival_time;
            int completionTime = currentTime + highesPriorityTask.burst_time;
            int turnaroundTime = completionTime - highesPriorityTask.arrival_time;

            totalWaitingTime += waitingTime;
            totalCompletionTime += completionTime;
            totalTurnaroundTime += turnaroundTime;

            currentTime = completionTime;
            completedTasks++;
            readyQueue.remove(highesPriorityTask);

            System.out.print("Name: " + highesPriorityTask.name + " Arrival Time: "
                    + highesPriorityTask.arrival_time + " BurstTime: " + highesPriorityTask.burst_time
                    + " Priority: " + highesPriorityTask.priority + " Waiting Time: " + waitingTime
                    + " Completion Time: " + completionTime + " Turnaround Time: " + turnaroundTime);
            System.out.println();

        }
        System.out.println("Average waiting time: " + (totalWaitingTime / tasks.length));
        System.out.println("Average turnaround time: " + (totalTurnaroundTime / tasks.length));
        System.out.println("Average completion time: " + (totalCompletionTime / tasks.length));

    }

    public static void main(String args[]) {
        Priority s = new Priority();
        Task tasks[] = new Task[3];
        tasks[1] = new Task("p1", 0, 5, 1);
        tasks[2] = new Task("p3", 5, 5, 2);
        tasks[0] = new Task("p2", 3, 5, 3);
        s.PriorityAlgo(tasks);
    }
}
