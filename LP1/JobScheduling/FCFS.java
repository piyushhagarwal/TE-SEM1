package LP1.JobScheduling;

class Task {
    public String name;
    public int arrival_time, burst_time, waiting_time, completion_time, turnaround_time;

    public Task(String name, int arrival_time, int burst_time) {
        this.name = name;
        this.arrival_time = arrival_time;
        this.burst_time = burst_time;
    }
}

public class FCFS {
    public static void FCFSSort(Task[] tasks) {
        int n = tasks.length;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (tasks[j].arrival_time > tasks[j + 1].arrival_time) {
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
                completion_time[i] = tasks[i].burst_time;
                turnaround_time[i] = completion_time[i] - tasks[i].arrival_time;
            } else {
                waiting_time[i] = completion_time[i - 1] - tasks[i].arrival_time;
                completion_time[i] = completion_time[i - 1] + tasks[i].burst_time;
                turnaround_time[i] = completion_time[i] - tasks[i].arrival_time;
            }
            total_waiting_time += waiting_time[i];
            total_turnaround_time += turnaround_time[i];
            total_completion_time += completion_time[i];
        }
        System.out.println("Task\tArrival Time\tBurst Time\tWaiting Time\tCompletion Time\tTurnaround Time");
        for (int i = 0; i < n; i++) {
            System.out.println(tasks[i].name + "\t" + tasks[i].arrival_time + "\t\t" + tasks[i].burst_time
                    + "\t\t" + waiting_time[i] + "\t\t" + completion_time[i] + "\t\t" + turnaround_time[i]);
        }
        System.out.println("Average waiting time: " + (total_waiting_time / n));
        System.out.println("Average turnaround time: " + (total_turnaround_time / n));
        System.out.println("Average completion time: " + (total_completion_time / n));
    }

}
