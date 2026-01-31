package project20280.list;

import project20280.interfaces.List;

import java.util.Iterator;

public class SinglyLinkedList<E> implements List<E>, Iterator<E> {

    private static class Node<E> {

        private E element;            // reference to the element stored at this node

        /**
         * A reference to the subsequent node in the list
         */
        private Node<E> next;         // reference to the subsequent node in the list

        /**
         * Creates a node with the given element and next node.
         *
         * @param e the element   to be stored
         * @param n reference to a node that should follow the new node
         */
        public Node(E e, Node<E> n) {
            // TODO
            element = e;
            next = n;
        }

        // Accessor methods

        /**
         * Returns the element stored at the node.
         *
         * @return the element stored at the node
         */
        public E getElement() {
            return element;
        }

        /**
         * Returns the node that follows this one (or null if no such node).
         *
         * @return the following node
         */
        public Node<E> getNext() {
            // TODO
            return next;
        }

        // Modifier methods

        /**
         * Sets the node's next reference to point to Node n.
         *
         * @param n the node that should follow this one
         */
        public void setNext(Node<E> n) {
            // TODO
            next = n;
        }
    } //----------- end of nested Node class -----------

    /**
     * The head node of the list
     */
    private Node<E> head = null;               // head node of the list (or null if empty)


    /**
     * Number of nodes in the list
     */
    private int size = 0;                      // number of nodes in the list

    public SinglyLinkedList() {
    }              // constructs an initially empty list

    //@Override
    public int size() {
        // TODO
        return size;
    }

    //@Override
    public boolean isEmpty() {
        // TODO
        return head == null;
    }

    @Override
    public E get(int position) {
        // TODO
        int count=0;
        Node<E> curr = head;
        while(curr!=null && position !=count) {
            count++;
            curr=curr.getNext();
        }

        return curr.getElement();
    }

    @Override
    public void add(int position, E e) {
        // TODO

        if(position == 0) {
            addFirst(e);
            return;
        }
        int count = 0;
        Node<E> curr = head;
        while(position-1 != count && curr!=null){
            count++;
            curr = curr.getNext();
        }

       

        Node<E> newNode = new Node<E>(e, curr.getNext());
        
        curr.setNext(newNode);
        size++;
        

    }


    @Override
    public void addFirst(E e) {
        // TODO
        head = new Node<E>(e, head);
        size++;
    }

    @Override
    public void addLast(E e) {
        // TODO
        Node<E> newNode = new Node<E>(e, null);
        Node<E> last = head;
        if(last == null){
            head=newNode;
        }
        else {
            while(last.getNext() !=null) {
                last= last.getNext();
            }
            last.setNext(newNode);
        }
        size++;
    }

    @Override
    public E remove(int position) {
        // TODO
        if(position==0) {
            return removeFirst();
        }
        int count=0;
        Node<E> curr = head;
        while(position-1 != count && curr != null) {
            count++;
            curr = curr.getNext();
        }

        Node<E> toDel = curr.getNext();
        curr.setNext(toDel.getNext());
        size--;

        return toDel.getElement();
    }

    @Override
    public E removeFirst() {
        // TODO
        if(isEmpty()){
            return null;
        }

        E el = head.getElement();
        head = head.getNext();
        size--;
        return el;
    }

    @Override
    public E removeLast() {
        // TODO
        if(isEmpty()) {
            return null;
        }

        if(head.getNext() == null) {
            E el = head.getElement();
            head = null;
            size--;
            return el;
        }

        Node<E> curr = head;
        while(curr.getNext().getNext() != null) {
            curr = curr.getNext();
        }

        E el = curr.getNext().getElement();
        curr.setNext(null);
        size--;
        return el;
    }

    //@Override
    public Iterator<E> iterator() {
        return new SinglyLinkedListIterator<E>();
    }

    private class SinglyLinkedListIterator<E> implements Iterator<E> {
        Node<E> curr = (Node<E>) head;

        @Override
        public boolean hasNext() {
            return curr != null;
        }

        @Override
        public E next() {
            E res = curr.getElement();
            curr = curr.next;
            return res;
        }
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        Node<E> curr = head;
        while (curr != null) {
            sb.append(curr.getElement());
            if (curr.getNext() != null)
                sb.append(", ");
            curr = curr.getNext();
        }
        sb.append("]");
        return sb.toString();
    }

    public static void main(String[] args) {
        SinglyLinkedList<Integer> ll = new SinglyLinkedList<Integer>();
        System.out.println("ll " + ll + " isEmpty: " + ll.isEmpty());
        //LinkedList<Integer> ll = new LinkedList<Integer>();

        ll.addFirst(0);
        ll.addFirst(1);
        ll.addFirst(2);
        ll.addFirst(3);
        ll.addFirst(4);
        ll.addLast(-1);
        //ll.removeLast();
        //ll.removeFirst();
        //System.out.println("I accept your apology");
        //ll.add(3, 2);
        System.out.println(ll);
        ll.remove(5);
        System.out.println(ll);

    }

	@Override
	public boolean hasNext() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public E next() {
		// TODO Auto-generated method stub
		return null;
	}
}
