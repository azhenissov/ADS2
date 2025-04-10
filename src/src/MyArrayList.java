import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class MyArrayList<T> implements MyList<T> {
    private Object[] elements;
    private int length;

    public MyArrayList() {
        elements = new Object[5];
        length = 0;
    }
    @Override
    public void add(T item) {
        if (length == elements.length) {
            increaseBuffer();
        }
        elements[length++] = item;
    }

    @Override
    public void set(int index, T item) {
        checkIndex(index);
        elements[index] = item;
    }

    @Override
    public void add(int index, T item) {
        checkIndexForAdd(index);
        System.arraycopy(elements, index, elements, index + 1, length - index);
        elements[index] = item;
        length++;
    }

    @Override
    public void addFirst(T item) {
        add(0, item);
    }

    @Override
    public void addLast(T item) {
        add(length, item);
    }
    @Override
    public T get(int index) {
        checkIndex(index);
        return (T) elements[index];
    }
    @Override
    public T getFirst() {
        return get(0);
    }
    @Override
    public T getLast() {
        return get(length - 1);
    }

    @Override
    public void remove(int index) {
        checkIndex(index);
        System.arraycopy(elements, index + 1, elements, index, length - index - 1);
        elements[--length] = null;
    }

    @Override
    public void removeFirst() {
        remove(0);
    }

    @Override
    public void removeLast() {
        remove(length - 1);
    }

    @Override
    public void sort() {
        Arrays.sort((T[]) elements, 0, length);
    }

    @Override
    public int indexOf(Object object) {
        for (int i = 0; i < length; i++) {
            if((object == null && elements[i] == null) ||
                (object != null && object.equals(elements[i]))) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public int lastIndexOf(Object object) {
        for(int i = length - 1; i >= 0; i--) {
            if ((object == null && elements[i] == null) || (object != null && object.equals(elements[i]))) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public boolean exists(Object object) {
        return indexOf(object) != -1;
    }

    @Override
    public Object[] toArray() {
        return Arrays.copyOf(elements, length);
    }

    @Override
    public void clear() {
        Arrays.fill(elements, 0, length, null);
        length = 0;
    }

    @Override
    public int size() {
        return length;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private int cursor = 0;

            public boolean hasNext() {
                return cursor < length;
            }
            public T next() {
                if (!hasNext()) throw new NoSuchElementException();
                return (T) elements[cursor++];
            }
        };
    }

    private void increaseBuffer(){
        Object[] newElements = new Object[elements.length*2];
        for (int i = 0; i < elements.length; i++) {
            newElements[i] = elements[i];
        }
        elements = newElements;
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= length) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + length);
        }
    }
    private void checkIndexForAdd(int index) {
        if (index < 0 || index > length) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + length);
        }
    }
}
