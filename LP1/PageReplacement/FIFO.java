package LP1.PageReplacement;

import java.util.LinkedList;
import java.util.Queue;

public class FIFO {
    public static void main(String[] args) {
        int[] pages = { 1, 2, 3, 4, 1, 5, 6, 2, 7, 8, 7 };
        int capacity = 3;
        fifoPageReplacement(pages, capacity);
    }

    private static void fifoPageReplacement(int[] pages, int capacity) {
        Queue<Integer> frame = new LinkedList<>();
        int pageFaults = 0;
        int hits = 0;

        for (int page : pages) {
            if (!frame.contains(page)) {
                pageFaults++;
                if (frame.size() == capacity) {
                    frame.poll();
                }
                frame.add(page);
            } else {
                hits++;
            }
            printFrames(page, frame, hits, pageFaults);
        }
    }

    private static void printFrames(int page, Queue<Integer> frame, int hits, int pageFaults) {
        System.out.print("Page " + page + ": ");
        System.out.print("Frames: ");
        for (Integer p : frame) {
            System.out.print(p + " ");
        }
        System.out.println("Hits: " + hits + ", Page Faults: " + pageFaults);
    }
}
