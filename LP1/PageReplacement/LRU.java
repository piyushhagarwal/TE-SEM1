package LP1.PageReplacement;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class LRU {
    public static void main(String[] args) {
        int[] pages = { 1, 2, 3, 4, 1, 5, 6, 2, 7, 8, 7 };
        int capacity = 3;
        lruPageReplacement(pages, capacity);
    }

    private static void lruPageReplacement(int[] pages, int capacity) {
        List<Integer> lruList = new ArrayList<>();
        Set<Integer> pageSet = new HashSet<>();
        int hits = 0;
        int pageFaults = 0;

        for (int page : pages) {
            if (pageSet.contains(page)) {
                // Page hit
                hits++;
                lruList.remove(Integer.valueOf(page));
            } else {
                // Page fault
                pageFaults++;
                if (lruList.size() == capacity) {
                    int removedPage = lruList.remove(0);
                    pageSet.remove(removedPage);
                }
            }

            lruList.add(page);
            pageSet.add(page);
            printFrames(page, lruList, hits, pageFaults);
        }
    }

    private static void printFrames(int page, List<Integer> frames, int hits, int pageFaults) {
        System.out.print("Page " + page + ": ");
        System.out.print("Frames: ");
        for (Integer p : frames) {
            System.out.print(p + " ");
        }
        System.out.println("Hits: " + hits + ", Page Faults: " + pageFaults);
    }
}
