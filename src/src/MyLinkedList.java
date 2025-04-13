import java.util.Comparator;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class MyLinkedList<T extends Comparable<T>> implements MyList<T> {
    private Node<T> head; // reference to the first node
    private Node<T> tail; // reference to the last node
    private int size;     // number of elements in the list

    // Node class for doubly linked list
    private static class Node<T> {
        T item;         // value stored in the node
        Node<T> next;   // reference to the next node
        Node<T> prev;   // reference to the previous node

        Node(T item) {
            this.item = item;
        }
    }

    // Constructor initializes an empty list
    public MyLinkedList() {
        head = null;
        tail = null;
        size = 0;
    }

    // Sorts using natural order (calls comparator version)
    @Override
    public void sort() {
        sort(Comparator.naturalOrder());
    }

    // Returns node at specific index by traversing from head or tail
    private Node<T> getNodeAt(int index) {
        if (index < size / 2) { // If index is in the first half, traverse from head
            Node<T> current = head;
            for (int i = 0; i < index; i++) {
                current = current.next;
            }
            return current;
        } else { // If index is in the second half, traverse from tail
            Node<T> current = tail;
            for (int i = size - 1; i > index; i--) {
                current = current.prev;
            }
            return current;
        }
    }

    // Sets a value at the given index
    @Override
    public void set(int index, T element) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index out of bounds");
        }
        getNodeAt(index).item = element;
    }

    // Checks if an element exists in the list
    @Override
    public boolean exists(Object o) {
        Node<T> current = head;
        while (current != null) {
            if (current.item.equals(o)) {
                return true;
            }
            current = current.next;
        }
        return false;
    }

    // Returns the first element
    @Override
    public T getFirst() {
        if (head == null) {
            throw new NoSuchElementException("List is empty");
        }
        return head.item;
    }

    // Returns the last element
    @Override
    public T getLast() {
        if (tail == null) {
            throw new NoSuchElementException("List is empty");
        }
        return tail.item;
    }

    // Removes the last element
    @Override
    public void removeLast() {
        if (tail == null) {
            throw new IllegalStateException("List is empty");
        }
        if (tail.prev != null) {
            tail = tail.prev;
            tail.next = null;
        } else {
            head = null;
            tail = null;
        }
        size--;
    }

    // Clears the list
    @Override
    public void clear() {
        head = null;
        tail = null;
        size = 0;
    }

    // Adds element to the beginning of the list
    @Override
    public void addFirst(T element) {
        Node<T> newNode = new Node<>(element);
        if (head == null) {
            head = tail = newNode;
        } else {
            newNode.next = head;
            head.prev = newNode;
            head = newNode;
        }
        size++;
    }

    // Returns an iterator for the list
    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private Node<T> currentNode = head;

            @Override
            public boolean hasNext() {
                return currentNode != null;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                T item = currentNode.item;
                currentNode = currentNode.next;
                return item;
            }
        };
    }

    // Removes the first element
    @Override
    public void removeFirst() {
        if (head == null) {
            throw new IllegalStateException("List is empty");
        }
        if (head.next != null) {
            head = head.next;
            head.prev = null;
        } else {
            head = tail = null;
        }
        size--;
    }

    // Converts the list to an array
    @Override
    public Object[] toArray() {
        Object[] array = new Object[size];
        Node<T> current = head;
        int index = 0;
        while (current != null) {
            array[index++] = current.item;
            current = current.next;
        }
        return array;
    }

    // Adds an element to the end (uses add)
    @Override
    public void addLast(T element) {
        add(element);
    }

    // Returns index of the first occurrence
    @Override
    public int indexOf(Object o) {
        Node<T> current = head;
        int index = 0;
        while (current != null) {
            if (current.item.equals(o)) {
                return index;
            }
            current = current.next;
            index++;
        }
        return -1;
    }

    // Returns index of the last occurrence
    @Override
    public int lastIndexOf(Object o) {
        Node<T> current = tail;
        int index = size - 1;
        while (current != null) {
            if (current.item.equals(o)) {
                return index;
            }
            current = current.prev;
            index--;
        }
        return -1;
    }

    // Returns element at a given index
    @Override
    public T get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
        return getNodeAt(index).item;
    }

    // Sorts list using provided comparator
    @Override
    public void sort(Comparator<? super T> comparator) {
        if (size <= 1) {
            return; // already sorted
        }

        Object[] array = toArray();
        @SuppressWarnings("unchecked")
        T[] typedArray = (T[]) array;
        java.util.Arrays.sort(typedArray, comparator);

        Node<T> current = head;
        for (T item : typedArray) {
            current.item = item;
            current = current.next;
        }
    }

    // Returns the size of the list
    @Override
    public int size() {
        return size;
    }

    // Removes an element at a specific index
    @Override
    public void remove(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }

        Node<T> toRemove = getNodeAt(index);

        if (toRemove.prev != null) {
            toRemove.prev.next = toRemove.next;
        } else {
            head = toRemove.next;
        }

        if (toRemove.next != null) {
            toRemove.next.prev = toRemove.prev;
        } else {
            tail = toRemove.prev;
        }

        toRemove.next = null;
        toRemove.prev = null;
        size--;
    }

    // Adds an element at a specific index
    @Override
    public void add(int index, T element) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Index out of bounds");
        }

        if (index == 0) {
            addFirst(element);
        } else if (index == size) {
            addLast(element);
        } else {
            Node<T> newNode = new Node<>(element);
            Node<T> current = getNodeAt(index);
            newNode.next = current;
            newNode.prev = current.prev;
            current.prev.next = newNode;
            current.prev = newNode;
            size++;
        }
    }

    // Adds an element to the end of the list
    @Override
    public void add(T element) {
        Node<T> newNode = new Node<>(element);
        if (tail == null) {
            head = tail = newNode;
        } else {
            tail.next = newNode;
            newNode.prev = tail;
            tail = newNode;
        }
        size++;
    }
}
