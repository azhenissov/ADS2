import java.util.Arrays;
import java.util.Comparator;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * A generic resizable array implementation similar to ArrayList.
 * It supports typical list operations and sorting.
 *
 * @param <T> Type of elements stored, must be Comparable.
 */
public class MyArrayList<T extends Comparable<T>> implements MyList<T> {
    private static final int BASIC_COP = 10; // Default initial capacity
    private Object[] elements; // Internal array to store elements
    private int size; // Number of actual elements in the list

    /**
     * Default constructor initializes the list with default capacity.
     */
    public MyArrayList() {
        this.elements = new Object[BASIC_COP];
        this.size = 0;
    }

    /**
     * Constructor to initialize the list with a specific capacity.
     *
     * @param initialCapacity Initial size of the internal array
     */
    public MyArrayList(int initialCapacity) {
        if (initialCapacity <= 0) {
            throw new IllegalArgumentException("Initial capacity must be greater than 0.");
        }
        this.elements = new Object[initialCapacity];
        this.size = 0;
    }

    /**
     * Returns an iterator over elements of type T.
     */
    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private int currentIndex = 0;

            @Override
            public boolean hasNext() {
                return currentIndex < size;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return (T) elements[currentIndex++];
            }
        };
    }

    /**
     * Resizes the internal array to double its current capacity.
     */
    private void resizeArray() {
        int newCapacity = elements.length * 2;
        Object[] newElements = new Object[newCapacity];
        for (int i = 0; i < size; i++) {
            newElements[i] = elements[i];
        }
        elements = newElements;
    }

    /**
     * Adds an item to the end of the list.
     */
    @Override
    public void add(T item) {
        if (size == elements.length) {
            resizeArray();
        }
        elements[size++] = item;
    }

    /**
     * Sets an item at the specified index.
     */
    @Override
    public void set(int index, T item) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("index out of range");
        }
        elements[index] = item;
    }

    /**
     * Inserts an item at a specific index and shifts the elements.
     */
    @Override
    public void add(int index, T item) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Invalid index: " + index);
        }
        if (size == elements.length) {
            resizeArray();
        }
        for (int i = size - 1; i >= index; i--) {
            elements[i + 1] = elements[i];
        }
        elements[index] = item;
        size++;
    }

    /**
     * Adds an item at the beginning of the list.
     */
    @Override
    public void addFirst(T item) {
        add(0, item);
    }

    /**
     * Adds an item at the end of the list (same as add()).
     */
    @Override
    public void addLast(T item) {
        add(item);
    }

    /**
     * Retrieves the item at the specified index.
     */
    @Override
    public T get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index + " Size: " + size);
        }
        return (T) elements[index];
    }

    /**
     * Retrieves the first item in the list.
     */
    @Override
    public T getFirst() {
        if (size == 0) {
            throw new IllegalStateException("empty");
        }
        return get(0);
    }

    /**
     * Retrieves the last item in the list.
     */
    @Override
    public T getLast() {
        if (size == 0) {
            throw new IllegalStateException("empty");
        }
        return get(size - 1);
    }

    /**
     * Removes the item at the specified index and shifts the remaining items.
     */
    @Override
    public void remove(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
        for (int i = index; i < size - 1; i++) {
            elements[i] = elements[i + 1];
        }
        elements[size - 1] = null;
        size--;
    }

    /**
     * Removes the first item in the list.
     */
    @Override
    public void removeFirst() {
        if (size == 0) {
            throw new IllegalStateException("empty");
        }
        remove(0);
    }

    /**
     * Removes the last item in the list.
     */
    @Override
    public void removeLast() {
        if (size == 0) {
            throw new IllegalStateException("empty");
        }
        elements[--size] = null;
    }

    /**
     * Sorts the list using natural ordering (Comparable).
     */
    @Override
    public void sort() {
        if (size == 0) {
            return;
        }
        Object[] toSort = new Object[size];
        System.arraycopy(elements, 0, toSort, 0, size);
        Arrays.sort(toSort);
        System.arraycopy(toSort, 0, elements, 0, size);
    }

    /**
     * Sorts the list using a provided Comparator.
     */
    @Override
    public void sort(Comparator<? super T> comparator) {
        if (size == 0) {
            return;
        }
        Object[] toSort = new Object[size];
        System.arraycopy(elements, 0, toSort, 0, size);
        Arrays.sort(toSort, (Comparator) comparator);
        System.arraycopy(toSort, 0, elements, 0, size);
    }

    /**
     * Returns the index of the first occurrence of the specified object.
     */
    @Override
    public int indexOf(Object object) {
        for (int i = 0; i < size; i++) {
            if (elements[i].equals(object)) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Returns the index of the last occurrence of the specified object.
     */
    @Override
    public int lastIndexOf(Object object) {
        for (int i = size - 1; i >= 0; i--) {
            if (elements[i].equals(object)) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Checks if the list contains the specified object.
     */
    @Override
    public boolean exists(Object object) {
        return indexOf(object) != -1;
    }

    /**
     * Returns an array containing all elements in the list.
     */
    @Override
    public Object[] toArray() {
        Object[] array = new Object[size];
        System.arraycopy(elements, 0, array, 0, size);
        return array;
    }

    /**
     * Clears all elements from the list.
     */
    @Override
    public void clear() {
        for (int i = 0; i < size; i++) {
            elements[i] = null;
        }
        size = 0;
    }

    /**
     * Returns the number of elements in the list.
     */
    @Override
    public int size() {
        return size;
    }
}
