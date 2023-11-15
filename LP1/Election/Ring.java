package LP1.Election;

import java.util.ArrayList;

class RingElectionAlgorithm {
    public static void main(String[] args) {
        int[] processes = { 1, 2, 3, 4, 5 };
        int coordinator = ringElection(processes);
        System.out.println("Coordinator: " + coordinator);
    }

    private static int ringElection(int[] processes) {
        ArrayList<Integer> processList = new ArrayList<>();
        for (int process : processes) {
            processList.add(process);
        }

        int coordinator = processes[0];
        int i = 0;
        while (processList.size() > 1) {
            int process = processList.get(i);
            int nextProcess = processList.get((i + 1) % processList.size());
            if (process > nextProcess) {
                processList.remove((i + 1) % processList.size());
            } else {
                processList.remove(i);
            }
            i = (i + 1) % processList.size();
        }
        return processList.get(0);
    }

}
