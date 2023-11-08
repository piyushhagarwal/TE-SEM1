package LP1.JobScheduling;

import java.util.ArrayList;
import java.util.Scanner;

public class Scheduling {
    public Task[] tasks;

    public void getInput() {
        System.out.println("Enter the number of tasks: ");
        int n = Integer.parseInt(System.console().readLine());
        tasks = new Task[n];
        Scanner sc = new Scanner(System.in);
        for (int i = 0; i < n; i++) {
            System.out.println("Enter the name of task " + (i + 1) + ": ");
            String name = sc.nextLine();
            System.out.println("Enter the arrival time of task " + (i + 1) + ": ");
            int arrival_time = sc.nextInt();
            System.out.println("Enter the burst time of task " + (i + 1) + ": ");
            int burst_time = sc.nextInt();
            System.out.println("Enter the priority of task " + (i + 1) + ": ");
            int priority = sc.nextInt();
            tasks[i] = new Task(name, arrival_time, burst_time, priority);
        }
        sc.close();
    }

    public static void FCFSSort(Task[] tasks) {
        int n = tasks.length;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (tasks[j].getArrivalTime() > tasks[j + 1].getArrivalTime()) {
                    Task temp = tasks[j];
                    tasks[j] = tasks[j + 1];
                    tasks[j + 1] = temp;
                }
            }
        }
    }

    public static void FCFS(Task[] tasks) {
        // Tasks are sorted by arrival time
        FCFSSort(tasks);

        int n = tasks.length;
        int[] waiting_time = new int[n];
        int[] completion_time = new int[n];
        int[] turnaround_time = new int[n];
        int total_waiting_time = 0;
        int total_turnaround_time = 0;
        int total_completion_time = 0;
        for (int i = 0; i < n; i++) {
            if (i == 0) {
                waiting_time[i] = 0;
                completion_time[i] = tasks[i].getBurstTime();
                turnaround_time[i] = completion_time[i] - tasks[i].getArrivalTime();
            } else {
                waiting_time[i] = completion_time[i - 1] - tasks[i].getArrivalTime();
                completion_time[i] = completion_time[i - 1] + tasks[i].getBurstTime();
                turnaround_time[i] = completion_time[i] - tasks[i].getArrivalTime();
            }
            total_waiting_time += waiting_time[i];
            total_turnaround_time += turnaround_time[i];
            total_completion_time += completion_time[i];
        }
        System.out.println("Task\tArrival Time\tBurst Time\tWaiting Time\tCompletion Time\tTurnaround Time");
        for (int i = 0; i < n; i++) {
            System.out.println(tasks[i].getName() + "\t" + tasks[i].getArrivalTime() + "\t\t" + tasks[i].getBurstTime()
                    + "\t\t" + waiting_time[i] + "\t\t" + completion_time[i] + "\t\t" + turnaround_time[i]);
        }
        System.out.println("Average waiting time: " + (total_waiting_time / n));
        System.out.println("Average turnaround time: " + (total_turnaround_time / n));
        System.out.println("Average completion time: " + (total_completion_time / n));
    }

    public Task HighestPriority(ArrayList<Task> readyQueue) {
        int highestPriorityIndex = 0;
        for (int i = 1; i < readyQueue.size(); i++) {
            if (readyQueue.get(i).getPriority() > readyQueue.get(highestPriorityIndex).getPriority()) {
                highestPriorityIndex = i;
            }
        }
        return readyQueue.get(highestPriorityIndex);
    }

    public void Priority(Task tasks[]) {
        int totalWaitingTime = 0;
        int totalTurnaroundTime = 0;
        int totalCompletionTime = 0;
        ArrayList<Task> readyQueue = new ArrayList<>();
        ArrayList<Task> alreadyAddedTasks = new ArrayList<>();
        int completedTasks = 0;
        int currentTime = 0;
        while (completedTasks != tasks.length) {
            for (int i = 0; i < tasks.length; i++) {
                if ((tasks[i].getArrivalTime() <= currentTime) && (alreadyAddedTasks.contains(tasks[i]) == false)) {
                    readyQueue.add(tasks[i]);
                    alreadyAddedTasks.add(tasks[i]);
                }
            }

            Task highesPriorityTask = HighestPriority(readyQueue);

            int waitingTime = currentTime - highesPriorityTask.getArrivalTime();
            int completionTime = currentTime + highesPriorityTask.getBurstTime();
            int turnaroundTime = completionTime - highesPriorityTask.getArrivalTime();

            totalWaitingTime += waitingTime;
            totalCompletionTime += completionTime;
            totalTurnaroundTime += turnaroundTime;

            currentTime = completionTime;
            completedTasks++;
            readyQueue.remove(highesPriorityTask);

            System.out.print("Name: " + highesPriorityTask.getName() + " Arrival Time: "
                    + highesPriorityTask.getArrivalTime() + " BurstTime: " + highesPriorityTask.getBurstTime()
                    + " Priority: " + highesPriorityTask.getPriority() + " Waiting Time: " + waitingTime
                    + " Completion Time: " + completionTime + " Turnaround Time: " + turnaroundTime);
            System.out.println();

        }
        System.out.println("Average waiting time: " + (totalWaitingTime / tasks.length));
        System.out.println("Average turnaround time: " + (totalTurnaroundTime / tasks.length));
        System.out.println("Average completion time: " + (totalCompletionTime / tasks.length));

    }

    public static void main(String args[]) {
        Scheduling s = new Scheduling();
        Task tasks[] = new Task[3];
        tasks[1] = new Task("p1", 0, 5, 1);
        tasks[2] = new Task("p3", 5, 5, 2);
        tasks[0] = new Task("p2", 3, 5, 3);
        s.Priority(tasks);
    }
}
