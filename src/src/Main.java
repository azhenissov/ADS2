import java.util.Iterator;

/**
 * Application entry point that demonstrates data structures functionality.
 */
public class Main {
    public static void main(String[] args) {
        // Test min heap implementation
        testMinHeap();
    }

    /**
     * Demonstrates the functionality of the min heap implementation
     */
    private static void testMinHeap() {
        // Create a new min heap instance
        MyMinHeap<Integer> numberHeap = new MyMinHeap<>();

        // Add elements to the heap
        System.out.println("Adding elements to the heap...");
        numberHeap.insert(10);
        numberHeap.insert(20);
        numberHeap.insert(5);
        numberHeap.insert(15);
        numberHeap.insert(30);

        // Uncomment to view minimum element without extraction
        // System.out.println("Minimum element: " + numberHeap.getMin());

        // Uncomment to extract and view minimum element
        // System.out.println("Extracted minimum: " + numberHeap.extractMin());

        // Uncomment to view new minimum after extraction
        // System.out.println("New minimum: " + numberHeap.getMin());

        // Uncomment to view heap size
        // System.out.println("Heap size: " + numberHeap.size());

        // Uncomment to extract all elements in order
        // System.out.println("Extracting all elements:");
        // while (!numberHeap.isEmpty()) {
        //     System.out.println("Extracted: " + numberHeap.extractMin());
        // }

        // Check if heap is empty
        System.out.println("Is heap empty? " + numberHeap.isEmpty());
    }

    /**
     * Helper method to display all elements in a queue
     *
     * @param queue The queue to display
     */
    public static void displayQueue(MyQueue<Integer> queue) {
        if (queue.isEmpty()) {
            System.out.println("Queue is empty");
            return;
        }

        System.out.print("Queue contents: ");

        // Create temporary storage
        MyQueue<Integer> temp = new MyQueue<>();

        // Process all elements
        while (!queue.isEmpty()) {
            // Get current element
            Integer element = queue.deleteQueue();

            // Display the element
            System.out.print(element + " ");

            // Save for restoration
            temp.enterQueue(element);
        }

        System.out.println();

        // Restore original queue state
        while (!temp.isEmpty()) {
            queue.enterQueue(temp.deleteQueue());
        }
    }

    /**
     * Helper method to display all elements in a linked list
     *
     * @param list The linked list to display
     */
    public static void displayList(MyLinkedList<Integer> list) {
        if (list.size() == 0) {
            System.out.println("List is empty");
            return;
        }

        System.out.print("List contents: ");

        // Use iterator to access all elements
        Iterator<Integer> elementIterator = list.iterator();

        while (elementIterator.hasNext()) {
            System.out.print(elementIterator.next() + " ");
        }

        System.out.println();
    }
}