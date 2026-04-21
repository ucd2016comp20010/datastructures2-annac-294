package project20280.stacksqueues;

import project20280.interfaces.Queue;

/**
 * Realization of a circular FIFO queue as an adaptation of a
 * CircularlyLinkedList. This provides one additional method not part of the
 * general Queue interface. A call to rotate() is a more efficient simulation of
 * the combination enqueue(dequeue()). All operations are performed in constant
 * time.
 */

public class LinkedCircularQueue<E> implements Queue<E> {

    private static class Node<E> {
    	private E element;
    	private Node<E> next;
    	public Node(E e, Node<E> n) {
    		element = e;
    		next = n;
    	}
    	
    	public E getElement() {
    		return element;
    	}
    	
    	public Node<E> getNext() {
    		return next;
    	}
    	
    	public void setNext(Node<E> n) {
    		next = n;
    	}
    }
    
    
    
    private int size = 0;
    private Node<E> tail = null;

    @Override
    public int size() {
        // TODO Auto-generated method stub
        return size;
    }

    @Override
    public boolean isEmpty() {
        // TODO Auto-generated method stub
        return size == 0;
    }

    @Override
    public void enqueue(E e) {
        // TODO Auto-generated method stub
    	Node<E> newest = new Node<E>(e,null);
    	if(isEmpty()) {
    		newest.setNext(newest);
    	}
    	else {
    		newest.setNext(tail.getNext());
    		tail.setNext(newest);
    	}
    	tail = newest;
    	size++;
    }

    @Override
    public E first() {
        // TODO Auto-generated method stub
        if(isEmpty()){
        	return null;
        }
        return tail.getNext().getElement();
    }

    @Override
    public E dequeue() {
        // TODO Auto-generated method stub
        if(isEmpty()) {
        	return null;
        }
        
        Node<E> head = tail.getNext();
        if(head == tail) {
        	tail = null;
        }
        else {
        	tail.setNext(head.getNext());
        }
        size--;
        return head.getElement();
    }
    
    public static void main(String[] args) {
    	LinkedCircularQueue<Integer> q = new LinkedCircularQueue<>();
    	
    	q.enqueue(5);
    	q.enqueue(3);
    	q.enqueue(9);
    	q.enqueue(8);
    	
    	System.out.println("First element in queue: " + q.first());
    	System.out.println("Remove first element by dequeuing: " + q.dequeue());
    	System.out.println("New first element in queue: " + q.first());
    }

}
