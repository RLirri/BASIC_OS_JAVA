import java.util.*;

public class FIFO {

    public static double pageInterruptPercentage;

    public static void firstInFirstOut(int pageFrameSize, String[] pageSequence) {

        HashMap<String, Integer> pageFrame = new HashMap<>(pageFrameSize);
        int pageInterrupt = 0;

        for (int i = 0; i < pageFrameSize; i++)
            pageFrame.put(String.valueOf(i), i);
        System.out.println("Page Frame No.:\t\t" + pageFrame.keySet());

        for (int i = 0; i < pageSequence.length; i++) {
            // all page in current pageFrame, time+1
            pageFrame.replaceAll((page, time) -> time+=1);
            // add key if absent in pageFrame, else if present do nothing
            pageFrame.putIfAbsent(pageSequence[i], 1);
            if (pageFrame.size() > pageFrameSize){
                // compare time of each page, remove page which stay longest
                String pageLongestTime = Collections.max(pageFrame.entrySet(),
                        Map.Entry.comparingByValue()).getKey();
                pageFrame.remove(pageLongestTime);

                // "*" indicating page Interrupt
                System.out.print("*");
                pageInterrupt++;
            }
            // can view time by sout(pageFrame.values())
            System.out.println("Page Replacement " + (i+1) + " : " + pageFrame.keySet());
        }
        pageInterruptPercentage = pageInterrupt * 100.0/ pageSequence.length;
        System.out.printf("Page Interrupt Ratio: %s%.2f%s",
                pageInterrupt + "/" + pageSequence.length + "\nPage Interrupt Percentage: ",
                pageInterruptPercentage, "%\n");
    }
}
