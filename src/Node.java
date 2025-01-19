public class Node<T> {
// public:
    public Node<T> next;
    
    public T value;

    public Node(T input) {
        this.value = input;
        this.next = null;
    }

    // Constructor without value
    public Node() {
        this.value = null;
        this.next = null;
    }
}