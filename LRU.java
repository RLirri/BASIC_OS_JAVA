import java.util.ArrayList;
import java.util.Arrays;

public class LRU {
    public static double pageInterruptPercentage;

    public static void lestRecentUsed(int pageFrameSize, String[] pageSequence){
        // throwList -> first element will be thrown first, last element last
        ArrayList<String> pageFrame = new ArrayList<>(), throwList = new ArrayList<>();
        int pageInterrupt = 0;

        System.out.println("Page Frame No.:\t\t" + Arrays.toString(new int[]{0, 1, 2, 3}));

        for (int i = 0; i < pageSequence.length; i++) {
            // if any page is accessed recently, update page to the back of throwList (last to be thrown)
            throwList.remove(pageSequence[i]);
            throwList.add(pageSequence[i]);

            if (!pageFrame.contains(pageSequence[i])) {
                pageFrame.add(pageSequence[i]);
                System.out.print("*");
                pageInterrupt++;
            }
            if (pageFrame.size() > pageFrameSize) {
                // pageFrame will throw page according to the first element of throwList;
                // if a page is thrown, throwList will remove that page as well
                pageFrame.remove(throwList.get(0));
                throwList.remove(0);
            }
            // can view throwList by sout(Arrays.toString(throwList.toArray()))
            System.out.println("Page Replacement " + (i+1)  + " : " +
                    Arrays.toString(pageFrame.toArray()));
        }
        pageInterruptPercentage = pageInterrupt * 100.0/ pageSequence.length;
        System.out.printf("Page Interrupt Ratio: %s%.2f%s",
                pageInterrupt + "/" + pageSequence.length + "\nPage Interrupt Percentage: ",
                pageInterruptPercentage, "%\n");
    }
}
