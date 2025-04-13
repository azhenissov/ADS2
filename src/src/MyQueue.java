public class MyQueue<T extends Comparable<T>> {
    private MyLinkedList<T> list = new MyLinkedList<>();

    // Adds an element to the end of the queue
    public void enterQueue(T item) {
        list.addLast(item);
    }

    // Removes and returns the element from the front of the queue
    public T deleteQueue() {
        if (isEmpty()) {
            throw new IllegalStateException("Queue is empty");
        }
        T item = list.getFirst();
        list.removeFirst();
        return item;
    }

    // Returns the element at the front of the queue without removing it
    public T peek() {
        if (isEmpty()) {
            throw new IllegalStateException("Queue is empty");
        }
        return list.getFirst();
    }

    // Checks if the queue is empty
    public boolean isEmpty() {
        return list.size() == 0;
    }

    // Returns the number of elements in the queue
    public int size() {
        return list.size();
    }
}
