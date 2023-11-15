package LP1.PageReplacement;

import java.util.ArrayList;
import java.util.List;

public class Optimal {
    public static void main(String args[]) {

    }

    public static void OptimalAlgo(int[] pages, int capacity) {

        ArrayList<Integer> frame = new ArrayList<>();
        int hits = 0;
        int pageFaults = 0;

        for (int i = 0; i < pages.length; i++) {
            if (!frame.contains(pages[i])) {
                pageFaults++;
                if (frame.size() < capacity) {
                    frame.add(pages[i]);
                } else {
                    int index = toBeReplacedPage(frame, pages, i);
                    frame.set(index, pages[i]);
                }
            } else {
                hits++;
            }

            printFrames(pages[i], frame, hits, pageFaults);
        }

    }

    public static int toBeReplacedPage(ArrayList<Integer> frame, int[] pages, int index) {
        int farthestIndex = -1;
        int farthest = Integer.MIN_VALUE;
        for (int i = 0; i < frame.size(); i++) {
            int nextPageIndex = getNextAppearanceIndex(pages, index, frame.get(i));
            if (nextPageIndex == -1) {
                return i;
            }
            if (nextPageIndex > farthest) {
                farthest = nextPageIndex;
                farthestIndex = i;
            }
        }
        return farthestIndex;
    }

    private static int getNextAppearanceIndex(int[] pages, int currentIndex, int value) {
        for (int i = currentIndex + 1; i < pages.length; i++) {
            if (pages[i] == value) {
                return i;
            }
        }
        return -1;
    }

    private static void printFrames(int page, List<Integer> frame, int hits, int pageFaults) {
        System.out.print("Page " + page + ": ");
        System.out.print("Frames: ");
        for (Integer p : frame) {
            System.out.print(p + " ");
        }
        System.out.println("Hits: " + hits + ", Page Faults: " + pageFaults);
    }

}
