public class MyStack<T extends Comparable<T>> {
    private MyLinkedList<T> list = new MyLinkedList<>();

    // Pushes an element onto the top of the stack
    public void push(T item) {
        list.addFirst(item);
    }

    // Removes and returns the element from the top of the stack
    public T pop() {
        if (isEmpty()) {
            throw new IllegalStateException("Stack is empty");
        }
        T item = list.getFirst();
        list.removeFirst();
        return item;
    }

    // Returns the element on the top of the stack without removing it
    public T peek() {
        if (isEmpty()) {
            throw new IllegalStateException("Stack is empty");
        }
        return list.getFirst();
    }

    // Checks if the stack is empty
    public boolean isEmpty() {
        return list.size() == 0;
    }

    // Returns the number of elements in the stack
    public int size() {
        return list.size();
    }
}
