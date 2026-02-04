package project20280.list;

import project20280.interfaces.List;

import java.util.Iterator;

public class CircularlyLinkedList<E> implements List<E> {

    private class Node<T> {
        private final T data;
        private Node<T> next;

        public Node(T e, Node<T> n) {
            data = e;
            next = n;
        }

        public T getData() {
            return data;
        }

        public void setNext(Node<T> n) {
            next = n;
        }

        public Node<T> getNext() {
            return next;
        }
    }

    private Node<E> tail = null;
    private int size = 0;

    public CircularlyLinkedList() {

    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public E get(int i) {
        // TODO
    	if(isEmpty() || i <0 || i >=size) {
    		throw new IndexOutOfBoundsException();
    	}
    	
       
        Node<E> curr = tail.getNext();
        for(int count=0; count < i; count++) {
        	curr = curr.getNext();
        }
        
        return curr.getData();
    }

    /**
     * Inserts the given element at the specified index of the list, shifting all
     * subsequent elements in the list one position further to make room.
     *
     * @param i the index at which the new element should be stored
     * @param e the new element to be stored
     */
    @Override
    public void add(int i, E e) {
        // TODO
    	if(i <0 || i >size) {
    		throw new IndexOutOfBoundsException();
    	}
    	
    	if(i == 0) {
    		addFirst(e);
    		return;
    	}
    	
    	if(i == size) {
    		addLast(e);
    		return;
    	}
    	
    	Node<E> curr = tail.getNext();
    	for(int count=0; count<i-1; count++) {
    		curr = curr.getNext();
    	}
    	
    	Node<E> newNode = new Node<E>(e, curr.getNext());
    	
    	curr.setNext(newNode);
    	size++;
    }

    @Override
    public E remove(int i) {
        // TODO
    	if(isEmpty() || i <0 || i >=size) {
    		throw new IndexOutOfBoundsException();
    	}
    	
    	if(i == 0) {
    		return removeFirst();
    	}
    	
     	Node<E> curr = tail.getNext();
    	for(int count=0; count < i-1; count++) {
    		curr = curr.getNext();
    	}
    	
    	Node<E> toDel = curr.getNext();
    	curr.setNext(toDel.getNext());
    	
    	if(toDel == tail) {
    		tail = curr;
    	}
    	
    	size--;
        return toDel.getData();
    }

    public void rotate() {
        // TODO
    	if(tail != null) {
    		tail=tail.getNext();
    	}
    }

    private class CircularlyLinkedListIterator<E> implements Iterator<E> {
        Node<E> curr = (Node<E>) tail;

        @Override
        public boolean hasNext() {
            return curr != tail;
        }

        @Override
        public E next() {
            E res = curr.data;
            curr = curr.next;
            return res;
        }

    }

    @Override
    public Iterator<E> iterator() {
        return new CircularlyLinkedListIterator<E>();
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public E removeFirst() {
        // TODO
    	if(isEmpty()) {
    		return null;
    	}
    	
    	Node<E> head = tail.getNext();
    	
    	if(head == tail) {
    		E el = tail.getData();
    		tail = null;
    		size--;
    		return el;
    	}
    	
    	E el = head.getData();
    	tail.setNext(head.getNext());
    	
    	size--;
        return el;
    }

    @Override
    public E removeLast() {
        // TODO
    	if(isEmpty()) {
    		return null;
    	}
    	
    	if(tail.getNext() == tail) {
    		E el = tail.getData();
    		tail = null;
    		size--;
    		return el;
    	}
    	
    	
    	Node<E> curr = tail.getNext();
    	
    	while(curr.getNext() != tail) {
    		curr = curr.getNext();
    	}
    	
    	E el = tail.getData();
    	curr.setNext(tail.getNext());
    	tail = curr;
    	size--;
    	return el;
    
    }
    
    @Override
    public void addFirst(E e) {
        // TODO
    	if(tail == null) {
    		tail = new Node<E>(e, null);
    		tail.setNext(tail);
    	}
    	else {
    		Node<E> newNode = new Node<E>(e, tail.getNext());
    		tail.setNext(newNode);
    	}
    	size++;
    
    }

    @Override
    public void addLast(E e) {
        // TODO
    	//set prev to tails next as newnode 
    	addFirst(e); //add new node to beginning o list
    	tail = tail.getNext();  //scoot the tail to the new node so it becomes the last
    }


    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        Node<E> curr = tail;
        do {
            curr = curr.next;
            sb.append(curr.data);
            if (curr != tail) {
                sb.append(", ");
            }
        } while (curr != tail);
        sb.append("]");
        return sb.toString();
    }


    public static void main(String[] args) {
        CircularlyLinkedList<Integer> ll = new CircularlyLinkedList<Integer>();
        for (int i = 10; i < 20; ++i) {
            ll.addLast(i);
        }

        System.out.println(ll);

        ll.removeFirst();
        System.out.println(ll);

        ll.removeLast();
        System.out.println(ll);

        ll.rotate();
        System.out.println(ll);

        ll.removeFirst();
        ll.rotate();
        System.out.println(ll);

        ll.removeLast();
        ll.rotate();
        System.out.println(ll);

        for (Integer e : ll) {
            System.out.println("value: " + e);
        }

    }
}
