import java.util.Comparator;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * A doubly-linked list implementation that provides fast insertions and deletions.
 * @param <T> the type of elements stored in this list
 */
public class MyLinkedList<T extends Comparable<T>> implements MyList<T> {
    /**
     * Internal node class for the linked list
     */
    private class ListNode {
        T value;
        ListNode previous;
        ListNode next;

        ListNode(T value) {
            this.value = value;
            this.previous = null;
            this.next = null;
        }
    }

    // References to first and last nodes
    private ListNode firstNode;
    private ListNode lastNode;

    // Number of elements
    private int elementCount;

    /**
     * Creates an empty linked list
     */
    public MyLinkedList() {
        firstNode = null;
        lastNode = null;
        elementCount = 0;
    }

    /**
     * Finds a node at a specific position
     */
    private ListNode findNodeAt(int position) {
        if (position < 0 || position >= elementCount) {
            throw new IndexOutOfBoundsException("Position: " + position + ", Size: " + elementCount);
        }

        ListNode current;

        // Optimize traversal by starting from the closest end
        if (position < elementCount / 2) {
            // Start from the beginning
            current = firstNode;
            for (int i = 0; i < position; i++) {
                current = current.next;
            }
        } else {
            // Start from the end
            current = lastNode;
            for (int i = elementCount - 1; i > position; i--) {
                current = current.previous;
            }
        }

        return current;
    }

    @Override
    public void add(T element) {
        ListNode newNode = new ListNode(element);

        if (elementCount == 0) {
            // First element in the list
            firstNode = lastNode = newNode;
        } else {
            // Link new node at the end
            newNode.previous = lastNode;
            lastNode.next = newNode;
            lastNode = newNode;
        }

        elementCount++;
    }

    @Override
    public void set(int position, T element) {
        findNodeAt(position).value = element;
    }

    @Override
    public void add(int position, T element) {
        if (position < 0 || position > elementCount) {
            throw new IndexOutOfBoundsException("Position: " + position + ", Size: " + elementCount);
        }

        if (position == 0) {
            // Add at beginning
            addFirst(element);
        } else if (position == elementCount) {
            // Add at end
            addLast(element);
        } else {
            // Add in the middle
            ListNode successor = findNodeAt(position);
            ListNode predecessor = successor.previous;

            ListNode newNode = new ListNode(element);
            newNode.next = successor;
            newNode.previous = predecessor;

            predecessor.next = newNode;
            successor.previous = newNode;

            elementCount++;
        }
    }

    @Override
    public void addFirst(T element) {
        ListNode newNode = new ListNode(element);

        if (elementCount == 0) {
            // First element in the list
            firstNode = lastNode = newNode;
        } else {
            // Link new node at the beginning
            newNode.next = firstNode;
            firstNode.previous = newNode;
            firstNode = newNode;
        }

        elementCount++;
    }

    @Override
    public void addLast(T element) {
        add(element);
    }

    @Override
    public T get(int position) {
        return findNodeAt(position).value;
    }

    @Override
    public T getFirst() {
        if (elementCount == 0) {
            throw new NoSuchElementException("List is empty");
        }
        return firstNode.value;
    }

    @Override
    public T getLast() {
        if (elementCount == 0) {
            throw new NoSuchElementException("List is empty");
        }
        return lastNode.value;
    }

    @Override
    public void remove(int position) {
        ListNode nodeToRemove = findNodeAt(position);

        // Update links
        if (nodeToRemove.previous != null) {
            nodeToRemove.previous.next = nodeToRemove.next;
        } else {
            // Removing first node
            firstNode = nodeToRemove.next;
        }

        if (nodeToRemove.next != null) {
            nodeToRemove.next.previous = nodeToRemove.previous;
        } else {
            // Removing last node
            lastNode = nodeToRemove.previous;
        }

        // Help GC
        nodeToRemove.previous = null;
        nodeToRemove.next = null;

        elementCount--;
    }

    @Override
    public void removeFirst() {
        if (elementCount == 0) {
            throw new NoSuchElementException("List is empty");
        }

        if (elementCount == 1) {
            // Only one element
            firstNode = lastNode = null;
        } else {
            ListNode oldFirst = firstNode;
            firstNode = firstNode.next;
            firstNode.previous = null;

            // Help GC
            oldFirst.next = null;
        }

        elementCount--;
    }

    @Override
    public void removeLast() {
        if (elementCount == 0) {
            throw new NoSuchElementException("List is empty");
        }

        if (elementCount == 1) {
            // Only one element
            firstNode = lastNode = null;
        } else {
            ListNode oldLast = lastNode;
            lastNode = lastNode.previous;
            lastNode.next = null;

            // Help GC
            oldLast.previous = null;
        }

        elementCount--;
    }

    @Override
    public void sort() {
        sort(Comparator.naturalOrder());
    }

    @SuppressWarnings("unchecked")
    @Override
    public void sort(Comparator<? super T> comparator) {
        if (elementCount <= 1) {
            return; // Already sorted
        }

        // Convert to array for sorting
        T[] tempArray = (T[]) new Comparable[elementCount];
        ListNode current = firstNode;
        for (int i = 0; i < elementCount; i++) {
            tempArray[i] = current.value;
            current = current.next;
        }

        // Sort the array
        java.util.Arrays.sort(tempArray, comparator);

        // Copy back to linked list
        current = firstNode;
        for (int i = 0; i < elementCount; i++) {
            current.value = tempArray[i];
            current = current.next;
        }
    }

    @Override
    public int indexOf(Object target) {
        ListNode current = firstNode;
        int index = 0;

        while (current != null) {
            if (current.value.equals(target)) {
                return index;
            }
            current = current.next;
            index++;
        }

        return -1;
    }

    @Override
    public int lastIndexOf(Object target) {
        ListNode current = lastNode;
        int index = elementCount - 1;

        while (current != null) {
            if (current.value.equals(target)) {
                return index;
            }
            current = current.previous;
            index--;
        }

        return -1;
    }

    @Override
    public boolean exists(Object target) {
        return indexOf(target) != -1;
    }

    @Override
    public Object[] toArray() {
        Object[] result = new Object[elementCount];
        ListNode current = firstNode;

        for (int i = 0; i < elementCount; i++) {
            result[i] = current.value;
            current = current.next;
        }

        return result;
    }

    @Override
    public void clear() {
        // Help GC by breaking links
        ListNode current = firstNode;
        while (current != null) {
            ListNode next = current.next;
            current.previous = null;
            current.next = null;
            current = next;
        }

        firstNode = null;
        lastNode = null;
        elementCount = 0;
    }

    @Override
    public int size() {
        return elementCount;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private ListNode pointer = firstNode;

            @Override
            public boolean hasNext() {
                return pointer != null;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }

                T value = pointer.value;
                pointer = pointer.next;
                return value;
            }
        };
    }
}