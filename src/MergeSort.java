public class MergeSort<T extends Comparable<T>> {
// public:    
    private Node<T> sortedHead;

    public MergeSort(Node<T> head) {
        this.sortedHead = mergeSort(head);
    }

    public Node<T> getSortedHead() {
        return sortedHead;
    }

// private:
    private Node<T> mergeSort(Node<T> head) {
        if (head == null || head.next == null) {
            return head;
        }

        Node<T> mid = split(head);
        Node<T> left = mergeSort(head);
        Node<T> right = mergeSort(mid);

        return merge(left, right);
    }

    private Node<T> split(Node<T> head) {
        Node<T> slow = head;
        Node<T> fast = head.next;

        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        Node<T> mid = slow.next;
        slow.next = null;
        return mid;
    }

    private Node<T> merge(Node<T> left, Node<T> right) {
        Node<T> tmp = new Node<>();
        Node<T> tail = tmp;

        while (left != null && right != null) {
            if (left.value.compareTo(right.value) < 0) {
                tail.next = left;
                left = left.next;
            } else {
                tail.next = right;
                right = right.next;
            }
            tail = tail.next;
        }

        tail.next = (left != null) ? left : right;

        return tmp.next;
    }
}