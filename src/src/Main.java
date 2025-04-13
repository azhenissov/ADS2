import java.util.Iterator;

public class Main {
    public static void main(String[] args) {
        MyMinHeap<Integer> minHeap = new MyMinHeap<>();

        minHeap.insert(10);
        minHeap.insert(20);
        minHeap.insert(5);
        minHeap.insert(15);
        minHeap.insert(30);

        // System.out.println(minHeap.getMin()); 


        // System.out.println(minHeap.extractMin());


        // System.out.println(minHeap.getMin()); 

        // System.out.println(minHeap.size());

        // while (!minHeap.isEmpty()) {
        //     System.out.println(minHeap.extractMin());
        // }

        System.out.println(minHeap.isEmpty());
    }



    // helper to print queue
    public static void printQueue(MyQueue<Integer> queue) {
        MyQueue<Integer> temp = new MyQueue<>();

        while (!queue.isEmpty()) {
            int val = queue.deleteQueue();
            System.out.print(val + " ");
            temp.enterQueue(val);
        }

        System.out.println();

        while (!temp.isEmpty()) {
            queue.enterQueue(temp.deleteQueue());
        }
    }





    // helper to print linkedlist
    public static void printList(MyLinkedList<Integer> list) {
        Iterator<Integer> iterator = list.iterator();
        while (iterator.hasNext()) {
            System.out.print(iterator.next() + " ");
        }
        System.out.println();
    }
}