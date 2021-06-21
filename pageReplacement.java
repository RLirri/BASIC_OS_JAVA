
import java.io.IOException;
import java.util.Random;
import java.util.Scanner;

public class pageReplacement {
    // Read input
    static Scanner sc = new Scanner(System.in);
    int no_pages, page[], no_frames, frames[], faults, count;
    // int num_pages;  // ++
    double rate;
    // Constructor
    public pageReplacement() throws IOException {
        // read number of pages
        System.out.println("Enter number of pages (ex:20)");
        no_pages = sc.nextInt();
        // num_pages = sc.nextInt();
        page = new int[no_pages];
        // read number of framescc
        System.out.println("Enter number of frames (ex: 5)");
        no_frames = sc.nextInt();
        frames = new int[no_frames];
        count = 1;
    }
    // Function to reset the frame array
    void frame_reset() {
        int j;
        for (j = 0; j < no_frames; j++)
            frames[j] = 0;
        faults = 0;
        count = 1;
    }
    // Page reference randomly generating
    void page_reference() throws IOException {
        int i;
        // Rabdomly generating the string
        for (i = 0; i < no_pages; i++) {
            page[i] = 0 + (int) (Math.random() * ((9 - 0) + 1));
        }
        System.out.println("The reference string is");
        // Printing the string
        for (i = 0; i < no_pages; i++) {
            System.out.print(page[i]);
        }
        System.out.println();
        for (i = 0; i < no_frames; i++)
            frames[i] = -1;
    }

    // FIFO replacement
    void FIFO_replacement() {
        int i, j, k = 0;
        // Reset the frame set
        frame_reset();
        boolean found = false;
        for (i = 0; i < no_pages; i++) {
            for (j = 0; j < no_frames; j++) {
                if (page[i] == frames[j])
                    found = true;
            }
            if (found == false) {
                frames[k] = page[i];
                if (k == no_frames - 1)
                    k = 0;
                else
                    k++;
                faults++;
            }
            display();
            found = false;
        }
        System.out.println("Number of page faults = " + faults);
        System.out.println("Fault rate = " + ((double)faults / no_pages));
    }

    // LRU replacement
    void LRU_replacement() {
        int i, j, dur[], max;
        frame_reset();
        dur = new int[no_frames];
        boolean found = false;
        for (i = 0; i < no_pages; i++) {
            for (j = 0; j < no_frames; j++)
                dur[j]++;
            for (j = 0; j < no_frames; j++) {
                if (page[i] == frames[j]) {
                    found = true;
                    dur[j] = 0;
                }
            }
            if (found == false) {
                max = 0;
                for (j = 0; j < no_frames; j++) {
                    if (dur[j] > dur[max])
                        max = j;
                }
                frames[max] = page[i];
                dur[max] = 0;
                faults++;
            }
            display();
            found = false;
        }
        System.out.println("Number of page faults = " + faults);
        System.out.println("Fault rate = " + ((double)faults / no_pages));
    }

    // Disply function
    void display() {
        int i;
        System.out.print("Page frame " + count + " :");
        for (i = 0; i < no_frames; i++) {
            if (frames[i] == -1)
                System.out.print(" -");
            else
                System.out.print(" " + frames[i]);
        }
        System.out.print("\no_pages");
        count++;
    }

    public static void main(String[] args) throws IOException {
        int me;
        String ch;
        pageReplacement p = new pageReplacement();
        p.page_reference();
        do {
            System.out.println("1. FIFO");
            System.out.println("2. LRU");
            System.out.println("Enter option");
            me = sc.nextInt();
            switch (me) {
            case 1:
                p.FIFO_replacement();
                break;
            case 2:
                p.LRU_replacement();
                break;
            default:
                System.out.println("Invalid input");
            }
            System.out.println("Enter C to continue");
            sc.nextLine();
            ch = sc.nextLine();
        } while (ch.compareToIgnoreCase("c") == 0);
    }
}
