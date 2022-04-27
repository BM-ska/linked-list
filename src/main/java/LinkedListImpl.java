import java.util.HashSet;
import java.util.Set;

/**
 * @author Barbara Moczulska
 */
public class LinkedListImpl<T> implements LinkedList<T> {

    private ListNode<T> head;

    private int size;

    public LinkedListImpl() {
        this.head = new Node<>();
    }

    public LinkedListImpl(T data) {
        this.head = new Node<>(data);
        this.size = 1;
    }

    public LinkedListImpl(ListNode<T> node) {

        if (hasCycle(node)) {
            throw new LinkedListCycleException("Cycle detected");
        }

        this.head = node;

        int sizeNode = 1;
        ListNode<T> tail = node;
        while (tail.next() != null) {
            sizeNode++;
            tail = tail.next();
        }
        this.size = sizeNode;
    }

    @Override
    public void add(T data) {
        add(new Node<>(data));
    }

    @Override
    public void add(ListNode<T> newNode) {
        if (newNode == null || newNode.isEmpty()) {
            throw new NullPointerException("passed element is null");
        }

        if (hasCycle(newNode)) {
            throw new LinkedListCycleException("Cycle detected");
        }


        int sizeNode = 1;
        ListNode<T> tail = newNode;
        while (tail.next() != null) {
            sizeNode++;
            tail = tail.next();
        }

        if (head.isEmpty()) {
            head = newNode;
        } else {
            tail = head;
            while (tail.next() != null) {
                tail = tail.next();
            }
            tail.setNext(newNode);
        }

        if (hasCycle(newNode)) {
            throw new LinkedListCycleException("Cycle detected");
        }

        size += sizeNode;
    }

    @Override
    public boolean isEmpty() {
        return head.isEmpty();
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("LinkedList{");

        if (head.isEmpty()) {
            sb.append('}');
            return sb.toString();
        }

        sb.append('[');
        sb.append(head.data());

        ListNode<T> nextNode = head;
        while (nextNode.next() != null) {
            sb.append(", ");
            sb.append(nextNode.next().data());
            nextNode = nextNode.next();
        }

        sb.append("]}");
        return sb.toString();
    }

    boolean hasCycle(ListNode<T> node) {
        if (node == null || node.isEmpty()) {
            throw new NullPointerException("passed element is null");
        }

        Set<ListNode<T>> visitedNodes = new HashSet<>();

        ListNode<T> nextNode = node;
        while (nextNode.next() != null) {
            if (visitedNodes.contains(nextNode)) {
                return true;
            }

            visitedNodes.add(nextNode);
            nextNode = nextNode.next();

        }
        return false;
    }

    public static class Node<T> implements ListNode<T> {

        private T value;
        private ListNode<T> next;

        public Node() {
        }

        public Node(T value) {
            this.value = value;
        }

        @Override
        public T data() {
            return this.value;
        }

        @Override
        public ListNode<T> next() {
            return this.next;
        }

        @Override
        public ListNode<T> setNext(ListNode<T> next) {
            this.next = next;
            return this;
        }

        @Override
        public boolean isEmpty() {
            return this.value == null;
        }

    }

}
