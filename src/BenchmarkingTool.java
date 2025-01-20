import java.io.IOException;
import java.util.Random;
import java.util.Scanner;

public class BenchmarkingTool {
    private static final int MENU_ITEMS_NUM = 11;
    private List<Integer> list;
    private Scanner scanner;

    BenchmarkingTool() {
        list = new List<>();
        scanner = new Scanner(System.in);
    }

    public void run() {
        boolean running = true;
        while (running) {
            printMenu();

            switch (navigateMenu()) {
                case 1:
                    addRandomElements();
                    sleep(500);
                    break;
                case 2:
                    addElement();
                    sleep(500);
                    break;
                case 3:
                    removeElement();
                    sleep(500);
                    break;
                case 4:
                    printElement();
                    sleep(500);
                    break;
                case 5:
                    list.printAll();
                    sleep(500);
                    break;
                case 6:
                    list.printFirst();
                    sleep(500);
                    break;
                case 7:
                    list.printLast();
                    sleep(500);
                    break;
                case 8:
                    list.bubbleSort();
                    sleep(500);
                    break;
                case 9:
                    list.mergeSort();
                    sleep(500);
                    break;
                case 10:
                    benchmarkSorts();
                    sleep(500);
                    break;
                case 11:
                    running = false;
                    sleep(500);
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    sleep(500);
            }

            clearConsole();
        }
    }

    private int navigateMenu() {
        System.out.print("Enter your choice: ");
        return scanner.nextInt();
    }

    private void printMenu() {
        System.out.println("Menu:");
        System.out.println("1. Add x random elements with value between y and z");
        System.out.println("2. Add element");
        System.out.println("3. Remove element");
        System.out.println("4. Print element");
        System.out.println("5. Print all elements");
        System.out.println("6. Print first element");
        System.out.println("7. Print last element");
        System.out.println("8. Bubble sort");
        System.out.println("9. Merge sort");
        System.out.println("10. Benchmark bubble sort and merge sort (iterate x times with y elements each time)");
        System.out.println("11. Exit");
    }

    private void addRandomElements() {
        System.out.print("Enter the number of elements to add: ");
        int num = scanner.nextInt();

        System.out.print("Enter the minimum value: ");
        int min = scanner.nextInt();

        System.out.print("Enter the maximum value: ");
        int max = scanner.nextInt();

        addRandomElements(num, min, max);
    }

    private void addRandomElements(int num, int min, int max) {
        Random random = new Random();
        for (int i = 0; i < num; i++) {
            int randomValue = random.nextInt(max - min + 1) + min;
            list.push(randomValue);
        }
    }

    private void addElement() {
        System.out.print("Enter the value to add: ");
        int value = scanner.nextInt();
        list.push(value);
    }

    private void removeElement() {
        System.out.print("Enter the index of the element to remove: ");
        int index = scanner.nextInt();
        list.removeElement(index);
    }

    private void printElement() {
        System.out.print("Enter the index of the element to print: ");
        int index = scanner.nextInt();
        list.printElement(index);
    }

    private void benchmarkSorts() {
        System.out.print("Enter the number of iterations: ");
        int iterations = scanner.nextInt();

        System.out.print("Enter the number of elements for each iteration: ");
        int numElements = scanner.nextInt();

        long[] mergeSortTimes = new long[iterations];

        // Benchmark merge sort
        for (int i = 0; i < iterations; ++i) {
            System.out.println("Initializing list for iteration " + (i + 1) + "...");
            addRandomElements(numElements, 0, 10000);

            System.out.println("Sorting with Merge Sort...");
            long start = System.nanoTime();
            list.mergeSort();
            long end = System.nanoTime();

            long duration = end - start;
            System.out.println("Iteration " + (i + 1) + ": Merge Sort time = " + duration / 1_000_000.0 + " ms");

            mergeSortTimes[i] = duration;

            list = new List<>(); // Clear the list
        }

        // Calculate average merge sort time
        double averageMergeSortTime = 0;
        for (long time : mergeSortTimes) {
            averageMergeSortTime += time;
        }
        averageMergeSortTime /= iterations;
        System.out.println("\n\nAverage Merge Sort time: " + averageMergeSortTime / 1_000_000.0 + " ms");

        // Benchmark bubble sort (single iteration)
        System.out.println("\nRunning a singular Bubble Sort iteration...");
        addRandomElements(numElements, 0, 10000);

        long start = System.nanoTime();
        list.bubbleSort();
        long end = System.nanoTime();

        long bubbleSortTime = end - start;
        if (bubbleSortTime < 1_000_000_000) {
            System.out.println("\nBubble Sort time: " + bubbleSortTime / 1_000_000.0 + " ms");
        } else {
            System.out.println("\nBubble Sort time: " + bubbleSortTime / 1_000_000_000.0 + " s");
        }

        sleep(1000);
        System.out.println("\nPress 'Enter' to continue.");
        try {
            System.in.read();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void clearConsole() {
        System.out.print("\033[2J\033[1;1H");
    }

    private void sleep(int milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}