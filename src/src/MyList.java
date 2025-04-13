import java.util.Comparator;

public interface MyList<T> extends Iterable<T> {
    /** Adds an item to the end of the list. */
    void add(T item);

    /** Replaces the element at the specified position in this list with the specified element. */
    void set(int index, T item);

    /** Inserts the specified element at the specified position in this list. */
    void add(int index, T item);

    /** Inserts the specified element at the beginning of this list. */
    void addFirst(T item);

    /** Appends the specified element to the end of this list. */
    void addLast(T item);

    /** Returns the element at the specified position in this list. */
    T get(int index);

    /** Returns the first element in this list. */
    T getFirst();

    /** Returns the last element in this list. */
    T getLast();

    /** Removes the element at the specified position in this list. */
    void remove(int index);

    /** Removes and returns the first element from this list. */
    void removeFirst();

    /** Removes and returns the last element from this list. */
    void removeLast();

    /** Sorts this list according to the natural ordering of its elements. */
    void sort();

    void sort(Comparator<? super T> c); // Optional based on FAQ choice

    /** Returns the index of the first occurrence of the specified element in this list, or -1 if this list does not contain the element. */
    int indexOf(Object object);

    /** Returns the index of the last occurrence of the specified element in this list, or -1 if this list does not contain the element. */
    int lastIndexOf(Object object);

    /** Returns true if this list contains the specified element. */
    boolean exists(Object object);

    /** Returns an array containing all of the elements in this list in proper sequence (from first to last element). */
    Object[] toArray();

    /** Removes all of the elements from this list. */
    void clear();

    /** Returns the number of elements in this list. */
    int size();

    // iterator() method is inherited from Iterable<T>
}