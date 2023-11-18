package LP1.PageReplacement;

import java.util.ArrayList;
import java.util.HashMap;

public class LRU {
    public static void LRUPageReplacementAlgo(int[] pages, int capacity) {
        int hits = 0;
        int pageFaults = 0;
        ArrayList<Integer> frame = new ArrayList<>();

        // Iterate through the pages
        for (int i = 0; i < pages.length; i++) {
            if (frame.contains(pages[i])) {
                // Page hit: The page is already in the frame
                hits++;
            } else {
                if (frame.size() < capacity) {
                    // Frame has space: Add the page to the frame
                    frame.add(pages[i]);
                    pageFaults++;
                } else {
                    // Frame is full: Replace the LRU page with the current page
                    int frameIndexToBeReplaced = FrameIndexToBeReplaced(pages, frame, i);
                    frame.set(frameIndexToBeReplaced, pages[i]);
                    pageFaults++;
                }
            }
            // Print the current state of the frame, hits, and page faults
            PrintFrame(frame, hits, pageFaults);
        }
    }

    public static void PrintFrame(ArrayList<Integer> frame, int hits, int pageFaults) {
        System.out.println("Hits: " + hits);
        System.out.println("Page Faults: " + pageFaults);
        System.out.println("Frame: ");
        for (int num : frame) {
            System.out.print(num + " ");
        }
        System.out.println();
    }

    public static int FrameIndexToBeReplaced(int[] pages, ArrayList<Integer> frame, int index) {
        // Map to store the index of the LRU page in the pages array
        HashMap<Integer, Integer> map = new HashMap<>();

        // Initialize the map with -1
        for (int num : frame) {
            map.put(num, -1);
        }

        // Update the map with the index of the page in the pages array which is Least
        // Recently Used
        for (int i = index - 1; i >= 0; i--) {
            if (map.containsKey(pages[i]) && map.get(pages[i]) == -1) {
                map.put(pages[i], i);
            }
        }

        // Find the LRU page and its index in the frame
        int LRUIndex = Integer.MAX_VALUE;
        int valueToBeReplaced = -1;

        for (int num : map.keySet()) {
            if (map.get(num) < LRUIndex) {
                LRUIndex = map.get(num);
                valueToBeReplaced = num;
            }
        }

        // Return the index of the LRU page in the frame
        return frame.indexOf(valueToBeReplaced);
    }

    public static void main(String[] args) {
        // Test the LRU page replacement algorithm
        int[] pages = { 1, 2, 3, 4, 1, 2, 5, 1, 2, 3, 4 };
        int capacity = 3;
        LRUPageReplacementAlgo(pages, capacity);
    }
}
