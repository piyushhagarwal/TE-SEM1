package LP1.Election;

import java.util.ArrayList;

class Node {
    public int id;
    public boolean active;

    public Node(int id, boolean active) {
        this.id = id;
        this.active = active;
    }
}

public class Election {
    public static void ringElectionAlgo(ArrayList<Node> nodes) {

        // Purposely fail the current coOrdinator
        Node coOrdinator = null;
        int maxValue = -1;
        int coOrdinatorIndex = -1;
        for (int i = 0; i < nodes.size(); i++) {
            if (nodes.get(i).id > maxValue) {
                maxValue = nodes.get(i).id;
                coOrdinator = nodes.get(i);
                coOrdinatorIndex = i;
            }
        }

        System.out.println("Current Co-ordinator is: " + coOrdinator.id);
        System.out.println("Current Co-ordinator fails");
        coOrdinator.active = false;

        // Assign coOrdinator to the next node
        int nextCoOrdinatorIndex = (coOrdinatorIndex + 1) % (nodes.size());
        coOrdinator = nodes.get(nextCoOrdinatorIndex);
        coOrdinatorIndex = nextCoOrdinatorIndex;
        System.out.println("Election is organised by " + coOrdinator.id);

        int nextIndex = (coOrdinatorIndex + 1) % nodes.size();
        while (nextIndex != coOrdinatorIndex) {
            if (nodes.get(nextIndex).id > coOrdinator.id && nodes.get(nextIndex).active == true) {
                coOrdinator = nodes.get(nextIndex);
                System.out.println("Current Co-ordinator is: " + coOrdinator.id);
                coOrdinatorIndex = nextIndex;
            }
            nextIndex = (nextIndex + 1) % nodes.size();
        }

        System.out.println("Final coordinator is " + coOrdinator.id);
    }

    public static void BullyElectionAlgo(ArrayList<Node> nodes) {

        // Purposely fail the current coOrdinator
        Node coOrdinator = null;
        int maxValue = -1;
        int coOrdinatorIndex = -1;
        for (int i = 0; i < nodes.size(); i++) {
            if (nodes.get(i).id > maxValue) {
                maxValue = nodes.get(i).id;
                coOrdinator = nodes.get(i);
                coOrdinatorIndex = i;
            }
        }

        System.out.println("Current Co-ordinator is: " + coOrdinator.id);
        System.out.println("Current Co-ordinator fails");
        coOrdinator.active = false;

        // Assign coOrdinator to the next node
        int nextCoOrdinatorIndex = (coOrdinatorIndex + 1) % (nodes.size());
        coOrdinator = nodes.get(nextCoOrdinatorIndex);
        coOrdinatorIndex = nextCoOrdinatorIndex;
        System.out.println("Election is organised by " + coOrdinator.id);

        int maxId = coOrdinator.id;
        for (int i = 0; i < nodes.size(); i++) {
            if ((nodes.get(i) != coOrdinator) && (nodes.get(i).active == true) && (nodes.get(i).id > coOrdinator.id)) {
                System.out.println("Message is sent to " + nodes.get(i).id);
                if (maxId < nodes.get(i).id) {
                    maxId = nodes.get(i).id;
                }
            }
        }
        System.out.println("Co ordinator is " + maxId);

    }

    public static void main(String args[]) {
        ArrayList<Node> nodes = new ArrayList<>();
        nodes.add(new Node(2, true));
        nodes.add(new Node(4, true));
        nodes.add(new Node(3, true));
        nodes.add(new Node(1, true));
        nodes.add(new Node(5, true));

        // ringElectionAlgo(nodes);
        BullyElectionAlgo(nodes);
    }
}
