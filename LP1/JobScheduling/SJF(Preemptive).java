package LP1.JobScheduling;

import java.util.ArrayList;

class Task {
    public String name;
    public int arrival_time, burst_time, waiting_time, completion_time, turnaround_time;

    public Task() {
        name = " ";
        arrival_time = 0;
        burst_time = 0;
        waiting_time = 0;
        completion_time = 0;
        turnaround_time = 0;
    }

    public Task(String name, int arrival_time, int burst_time) {
        this.name = name;
        this.arrival_time = arrival_time;
        this.burst_time = burst_time;
    }
}

class SJFPreemptive {

    public static Task findShortestJob(ArrayList<Task> readyQueue) {
        int shortestTime = Integer.MAX_VALUE;
        Task shortestTask = null;
        for (Task task : readyQueue) {
            if (task.burst_time < shortestTime) {
                shortestTime = task.burst_time;
                shortestTask = task;
            }
        }
        return shortestTask;
    }

    public static void SJFPreemptiveAlgo(ArrayList<Task> tasks, int quantum) {

        int totalWaitingTime = 0;
        int totalTurnArountTime = 0;
        int totalCompletionTime = 0;

        ArrayList<Task> readyQueue = new ArrayList<>();
        for (Task task : tasks) {
            if (task.arrival_time == 0) {
                readyQueue.add(task);
            }
        }

        int currentTime = 0;

        while (!readyQueue.isEmpty()) {
            Task currentTask = findShortestJob(readyQueue);
            System.out.println("Current Task: " + currentTask.name);
            // Print readyQueue
            System.out.print("Ready Queue: ");
            for (Task task : readyQueue) {
                System.out.print(task.name + " ");
            }
            System.out.println();
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
                readyQueue.remove(currentTask);
            }

            for (Task task : tasks) {
                if (task.arrival_time <= currentTime && readyQueue.contains(task) == false && task.burst_time != 0) {
                    readyQueue.add(task);
                }
            }

            // Print the current state of the ready queue
            System.out.print("Ready Queue: ");
            for (Task task : readyQueue) {
                System.out.print(task.name + " ");
            }
            System.out.println();
        }

        // Print the table
        System.out.println("Task\tArrival Time\tBurst Time\tWaiting Time\tCompletion Time\tTurnaround Time");
        for (Task task : tasks) {
            System.out.println(task.name + "\t" + task.arrival_time + "\t\t" + task.burst_time + "\t\t"
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
        tasks.add(new Task("T3", 2, 4));
        tasks.add(new Task("T4", 3, 6));
        tasks.add(new Task("T5", 4, 4));
        SJFPreemptiveAlgo(tasks, 3);
    }
}
