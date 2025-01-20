import java.util.NoSuchElementException;

public class List<T extends Comparable<T>> {
    private Node<T> head; // Pointer to the first node

    // Constructor for an empty list
    public List() {
        this.head = null;
    }

    // Constructor with initial value
    public List(T value) {
        this.head = new Node<>(value);
    }

    // Push to add object to list
    public void push(T input) {
        if (head == null) {
            head = new Node<>(input);
        } else {
            Node<T> current = head;
            while (current.next != null) {
                current = current.next;
            }
            current.next = new Node<>(input);
        }
    }

    // Push at index
    public void pushAt(T input, int index) {
        if (index < 0 || index > getSize()) {
            throw new IndexOutOfBoundsException("Push index " + index + " out of range");
        }

        Node<T> push = new Node<>(input);
        if (index == 0) {
            push.next = head;
            head = push;
            return;
        }

        Node<T> current = head;
        for (int i = 0; i < index - 1; i++) {
            current = current.next;
        }
        push.next = current.next;
        current.next = push;
    }

    // Pull element at index
    public T pull(int index) {
        if (index < 0 || index >= getSize()) {
            throw new IndexOutOfBoundsException("Index " + index + " out of range");
        }

        Node<T> current = head;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }
        return current.value;
    }

    // Remove element at index
    public void removeElement(int index) {
        if (index < 0 || index >= getSize()) {
            throw new IndexOutOfBoundsException("Element index " + index + " out of range");
        }

        if (head == null) {
            throw new NoSuchElementException("List is empty");
        } else if (index == 0) {
            head = head.next;
        } else {
            Node<T> current = head;
            Node<T> previous = null;
            for (int i = 0; i < index; i++) {
                previous = current;
                current = current.next;
            }
            previous.next = current.next;
        }
    }

    // Get size of the list
    public int getSize() {
        Node<T> current = head;
        int size = 0;
        while (current != null) {
            size++;
            current = current.next;
        }
        return size;
    }

    // Print element at index
    public void printElement(int index) {
        if (index < 0) {
            System.out.println("Index can't be negative");
            return;
        }
        if (head == null) {
            System.out.println("List is empty");
        } else {
            Node<T> current = head;
            for (int i = 0; i < index; i++) {
                if (current.next == null) {
                    System.out.println("Index " + index + " out of range");
                    return;
                }
                current = current.next;
            }
            System.out.println("Element " + index + " value: " + current.value);
        }
    }

    // Remove all elements from list
    public void clear() {
        head = null;
    }

    // Print last element
    public void printLast() {
        if (head != null) {
            Node<T> current = head;
            while (current.next != null) {
                current = current.next;
            }
            System.out.println("Tail value: " + current.value);
        } else {
            System.out.println("List is empty");
        }
    }

    // Print first element
    public void printFirst() {
        if (head != null) {
            System.out.println("Head value: " + head.value);
        } else {
            System.out.println("List is empty");
        }
    }

    // Print all elements
    public void printAll() {
        if (head != null) {
            Node<T> current = head;
            while (current != null) {
                System.out.println(current.value);
                current = current.next;
            }
        } else {
            System.out.println("List is empty");
        }
    }

    // Bubble sort the list
    public void bubbleSort() {
        int size = getSize();
        if (size < 2) {
            return;
        }
        Node<T> current;
        T temporary;
        for (int i = 0; i < size - 1; i++) {
            current = head;
            for (int j = 0; j < size - i - 1; j++) {
                if (((Comparable<T>) current.value).compareTo(current.next.value) > 0) {
                    temporary = current.value;
                    current.value = current.next.value;
                    current.next.value = temporary;
                }
                current = current.next;
            }
        }
    }

    // Merge sort the list
    public void mergeSort() {
        MergeSort<T> mergeSort = new MergeSort<>(head);
        head = mergeSort.getSortedHead();
    }
}