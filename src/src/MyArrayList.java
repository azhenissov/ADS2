import java.util.Arrays;
import java.util.Comparator;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * A flexible growing array implementation that provides list functionality.
 * @param <T> the type of elements stored in this list
 */
public class MyArrayList<T extends Comparable<T>> implements MyList<T> {
    // Default capacity for new instances
    private static final int DEFAULT_CAPACITY = 10;

    // Internal storage
    private Object[] data;

    // Current number of elements
    private int currentSize;

    /**
     * Creates an empty list with default capacity
     */
    public MyArrayList() {
        data = new Object[DEFAULT_CAPACITY];
        currentSize = 0;
    }

    /**
     * Creates an empty list with specified capacity
     */
    public MyArrayList(int startingCapacity) {
        if (startingCapacity < 1) {
            throw new IllegalArgumentException("Starting capacity must be positive");
        }
        data = new Object[startingCapacity];
        currentSize = 0;
    }

    /**
     * Ensures that the internal array has enough space for adding elements
     */
    private void ensureCapacity() {
        if (currentSize >= data.length) {
            // Double the capacity when needed
            int newCapacity = data.length * 2;
            data = Arrays.copyOf(data, newCapacity);
        }
    }

    @Override
    public void add(T element) {
        ensureCapacity();
        data[currentSize++] = element;
    }

    @Override
    public void set(int position, T element) {
        validateIndex(position);
        data[position] = element;
    }

    @Override
    public void add(int position, T element) {
        validateIndexForInsertion(position);
        ensureCapacity();

        // Shift elements to make room
        System.arraycopy(data, position, data, position + 1, currentSize - position);
        data[position] = element;
        currentSize++;
    }

    @Override
    public void addFirst(T element) {
        add(0, element);
    }

    @Override
    public void addLast(T element) {
        add(element);
    }

    @SuppressWarnings("unchecked")
    @Override
    public T get(int position) {
        validateIndex(position);
        return (T) data[position];
    }

    @Override
    public T getFirst() {
        if (isEmpty()) {
            throw new NoSuchElementException("List is empty");
        }
        return get(0);
    }

    @Override
    public T getLast() {
        if (isEmpty()) {
            throw new NoSuchElementException("List is empty");
        }
        return get(currentSize - 1);
    }

    @Override
    public void remove(int position) {
        validateIndex(position);

        // Shift elements to fill the gap
        int numToMove = currentSize - position - 1;
        if (numToMove > 0) {
            System.arraycopy(data, position + 1, data, position, numToMove);
        }

        // Clear the last element reference and decrement size
        data[--currentSize] = null;
    }

    @Override
    public void removeFirst() {
        if (isEmpty()) {
            throw new NoSuchElementException("List is empty");
        }
        remove(0);
    }

    @Override
    public void removeLast() {
        if (isEmpty()) {
            throw new NoSuchElementException("List is empty");
        }
        data[--currentSize] = null;
    }

    @SuppressWarnings("unchecked")
    @Override
    public void sort() {
        if (currentSize <= 1) {
            return;
        }

        // Create a temporary array of the actual elements
        T[] temp = (T[]) new Comparable[currentSize];
        for (int i = 0; i < currentSize; i++) {
            temp[i] = (T) data[i];
        }

        Arrays.sort(temp);

        // Copy the sorted elements back
        for (int i = 0; i < currentSize; i++) {
            data[i] = temp[i];
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public void sort(Comparator<? super T> comparator) {
        if (currentSize <= 1) {
            return;
        }

        // Create a temporary array of the actual elements
        T[] temp = (T[]) new Comparable[currentSize];
        for (int i = 0; i < currentSize; i++) {
            temp[i] = (T) data[i];
        }

        Arrays.sort(temp, comparator);

        // Copy the sorted elements back
        for (int i = 0; i < currentSize; i++) {
            data[i] = temp[i];
        }
    }

    @Override
    public int indexOf(Object target) {
        for (int i = 0; i < currentSize; i++) {
            if (data[i] != null && data[i].equals(target)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public int lastIndexOf(Object target) {
        for (int i = currentSize - 1; i >= 0; i--) {
            if (data[i] != null && data[i].equals(target)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public boolean exists(Object target) {
        return indexOf(target) >= 0;
    }

    @Override
    public Object[] toArray() {
        return Arrays.copyOf(data, currentSize);
    }

    @Override
    public void clear() {
        // Clear all references to help GC
        for (int i = 0; i < currentSize; i++) {
            data[i] = null;
        }
        currentSize = 0;
    }

    @Override
    public int size() {
        return currentSize;
    }

    /**
     * Checks if the list is empty
     */
    private boolean isEmpty() {
        return currentSize == 0;
    }

    /**
     * Validates that the provided index is within bounds
     */
    private void validateIndex(int index) {
        if (index < 0 || index >= currentSize) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + currentSize);
        }
    }

    /**
     * Validates that the provided index is valid for insertion
     */
    private void validateIndexForInsertion(int index) {
        if (index < 0 || index > currentSize) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + currentSize);
        }
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private int cursor = 0;

            @Override
            public boolean hasNext() {
                return cursor < currentSize;
            }

            @SuppressWarnings("unchecked")
            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return (T) data[cursor++];
            }
        };
    }
}