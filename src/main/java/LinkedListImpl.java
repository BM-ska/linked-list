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

    public LinkedListImpl(T i) {
        this.head = new Node<>(i);
        this.size = 1;
    }

    public LinkedListImpl(ListNode<T> node) {

        if(hasCycle(node))
            throw new LinkedListCycleException("Cycle detected");

        this.head = node;

        int sizeNode = 1;
        ListNode<T> tail = node;
        while (tail.next() != null) {
            sizeNode ++;
            tail = tail.next();
        }
        this.size = sizeNode;
    }

    @Override
    public void add(T i) {
        add(new Node<>(i));
    }

    @Override
    public void add(ListNode<T> newNode) {
        if (newNode == null || newNode.isEmpty()) {
            throw new NullPointerException("passed element is null");
        }

        if(hasCycle(newNode))
            throw new LinkedListCycleException("Cycle detected");


        int sizeNode = 1;
        ListNode<T> tail = newNode;
        while (tail.next() != null) {
            sizeNode ++;
            tail = tail.next();
        }

        if (head.isEmpty()) {
            head = newNode;
        } else {
            ListNode<T> tail1 = head;
            while (tail1.next() != null) {
                tail1 = tail1.next();
            }
            tail1.setNext(newNode);
        }

        if(hasCycle(newNode))
            throw new LinkedListCycleException("Cycle detected");

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
        StringBuilder lista = new StringBuilder();
        lista.append("LinkedList{");

        if (head.isEmpty()) {
            lista.append('}');
            return lista.toString();
        }

        lista.append('[');
        lista.append(head.data());

        ListNode<T> nextNode = head;
        while (nextNode.next() != null) {
            lista.append(", ");
            lista.append(nextNode.next().data());
            nextNode = nextNode.next();
        }

        lista.append("]}");
        return lista.toString();
    }

    boolean hasCycle ( ListNode<T> node )
    {
        if (node == null || node.isEmpty()) {
            throw new NullPointerException("passed element is null");
        }

        Set<ListNode<T>> treeSet = new HashSet<>();

        ListNode<T> nextNode = node;
        while (nextNode.next() != null) {
            if(treeSet.contains(nextNode))
                return true;

            treeSet.add(nextNode);
            nextNode = nextNode.next();

        }
        return  false;
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
            return this.next;
        }

        @Override
        public boolean isEmpty() {
            return this.value == null;
        }

    }



}
