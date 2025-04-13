public class MyMinHeap<T extends Comparable<T>> {
    private MyArrayList<T> heap = new MyArrayList<>();

    // Inserts a new element into the heap
    public void insert(T item) {
        heap.add(item);                   // Add to the end
        heapifyUp(heap.size() - 1);       // Restore heap property upwards
    }

    // Removes and returns the minimum element (root)
    public T extractMin() {
        if (isEmpty()) {
            throw new IllegalStateException("Heap is empty");
        }

        T min = heap.get(0);                          // The root element
        T lastItem = heap.get(heap.size() - 1);       // Last element in the heap
        heap.set(0, lastItem);                        // Move last element to root
        heap.removeLast();                            // Remove last element
        heapifyDown(0);                               // Restore heap property downwards
        return min;
    }

    // Returns the minimum element without removing it
    public T getMin() {
        if (isEmpty()) {
            throw new IllegalStateException("Heap is empty");
        }
        return heap.get(0);
    }

    // Checks if the heap is empty
    public boolean isEmpty() {
        return heap.size() == 0;
    }

    // Returns the number of elements in the heap
    public int size() {
        return heap.size();
    }

    // Restores the heap property by shifting the element at 'index' up
    private void heapifyUp(int index) {
        while (index > 0) {
            int parent = (index - 1) / 2;
            if (heap.get(index).compareTo(heap.get(parent)) < 0) {
                swap(index, parent);
                index = parent;
            } else {
                break;
            }
        }
    }

    // Restores the heap property by shifting the element at 'index' down
    private void heapifyDown(int index) {
        int size = heap.size();
        while (index < size) {
            int left = 2 * index + 1;
            int right = 2 * index + 2;
            int smallest = index;

            if (left < size && heap.get(left).compareTo(heap.get(smallest)) < 0) {
                smallest = left;
            }
            if (right < size && heap.get(right).compareTo(heap.get(smallest)) < 0) {
                smallest = right;
            }

            if (smallest != index) {
                swap(index, smallest);
                index = smallest;
            } else {
                break;
            }
        }
    }

    // Swaps elements at indices i and j in the heap
    private void swap(int i, int j) {
        T temp = heap.get(i);
        heap.set(i, heap.get(j));
        heap.set(j, temp);
    }
}
