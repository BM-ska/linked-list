public interface ListNode<T> {
    T data();

    ListNode<T> next();

    ListNode<T> setNext(ListNode<T> next);

    boolean isEmpty();
}


