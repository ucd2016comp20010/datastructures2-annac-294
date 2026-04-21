package project20280.tree;

import project20280.interfaces.Position;

import java.util.ArrayList;
import java.util.List;

//import org.apache.commons.math3.stat.descriptive.DescriptiveStatistics;

/**
 * Concrete implementation of a binary tree using a node-based, linked
 * structure.
 */
public class LinkedBinaryTree<E> extends AbstractBinaryTree<E> {

    static java.util.Random rnd = new java.util.Random();
    /**
     * The root of the binary tree
     */
    protected Node<E> root = null; // root of the tree

    // LinkedBinaryTree instance variables
    /**
     * The number of nodes in the binary tree
     */
    private int size = 0; // number of nodes in the tree

    /**
     * Constructs an empty binary tree.
     */
    public LinkedBinaryTree() {
    } // constructs an empty binary tree

    // constructor

    public static LinkedBinaryTree<Integer> makeRandom(int n) {
        LinkedBinaryTree<Integer> bt = new LinkedBinaryTree<>();
        bt.root = randomTree(null, 1, n);
        return bt;
    }

    // nonpublic utility

    public static <T extends Integer> Node<T> randomTree(Node<T> parent, Integer first, Integer last) {
        if (first > last) return null;
        else {
            Integer treeSize = last - first + 1;
            Integer leftCount = rnd.nextInt(treeSize);
            Integer rightCount = treeSize - leftCount - 1;
            Node<T> root = new Node<T>((T) ((Integer) (first + leftCount)), parent, null, null);
            root.setLeft(randomTree(root, first, first + leftCount - 1));
            root.setRight(randomTree(root, first + leftCount + 1, last));
            return root;
        }
    }

    // accessor methods (not already implemented in AbstractBinaryTree)

    public static void main(String [] args) {
    	//LAB 4
    	LinkedBinaryTree<String> bt = new LinkedBinaryTree<>();
        String[] arr = { "A", "B", "C", "D", "E", null, "F", null, null, "G", "H", null, null, null, null };
        bt.createLevelOrder(arr);
        System.out.println(bt.toBinaryTreeString());
        
        	
    	Integer [] inorder= {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30};
    	Integer [] preorder= {18, 2, 1, 14, 13, 12, 4, 3, 9, 6, 5, 8, 7, 10, 11, 15, 16, 17, 28, 23, 19, 22, 20, 21, 24, 27, 26, 25, 29, 30};
    	
    	LinkedBinaryTree <Integer > btTest2 = new LinkedBinaryTree <>();
    	btTest2.construct(inorder , preorder);
    	System.out.println(btTest2.toBinaryTreeString());
    	
    }


    /**
     * Factory function to create a new node storing element e.
     */
    protected Node<E> createNode(E e, Node<E> parent, Node<E> left, Node<E> right) {
        return new Node<E>(e, parent, left, right);
    }

    /**
     * Verifies that a Position belongs to the appropriate class, and is not one
     * that has been previously removed. Note that our current implementation does
     * not actually verify that the position belongs to this particular list
     * instance.
     *
     * @param p a Position (that should belong to this tree)
     * @return the underlying Node instance for the position
     * @throws IllegalArgumentException if an invalid position is detected
     */
    protected Node<E> validate(Position<E> p) throws IllegalArgumentException {
        if (!(p instanceof Node)) throw new IllegalArgumentException("Not valid position type");
        Node<E> node = (Node<E>) p; // safe cast
        if (node.getParent() == node) // our convention for defunct node
            throw new IllegalArgumentException("p is no longer in the tree");
        return node;
    }

    /**
     * Returns the number of nodes in the tree.
     *
     * @return number of nodes in the tree
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * Returns the root Position of the tree (or null if tree is empty).
     *
     * @return root Position of the tree (or null if tree is empty)
     */
    @Override
    public Position<E> root() {
        return root;
    }

    // update methods supported by this class

    /**
     * Returns the Position of p's parent (or null if p is root).
     *
     * @param p A valid Position within the tree
     * @return Position of p's parent (or null if p is root)
     * @throws IllegalArgumentException if p is not a valid Position for this tree.
     */
    @Override
    public Position<E> parent(Position<E> p) throws IllegalArgumentException {
        if(p == null) {
        	throw new IllegalArgumentException("Position cannot be null");
        }
    	
    	return ((Node<E>) p).getParent();
    }

    /**
     * Returns the Position of p's left child (or null if no child exists).
     *
     * @param p A valid Position within the tree
     * @return the Position of the left child (or null if no child exists)
     * @throws IllegalArgumentException if p is not a valid Position for this tree
     */
    @Override
    public Position<E> left(Position<E> p) throws IllegalArgumentException {
        return ((Node<E>) p).getLeft();
    }

    /**
     * Returns the Position of p's right child (or null if no child exists).
     *
     * @param p A valid Position within the tree
     * @return the Position of the right child (or null if no child exists)
     * @throws IllegalArgumentException if p is not a valid Position for this tree
     */
    @Override
    public Position<E> right(Position<E> p) throws IllegalArgumentException {
        return ((Node<E>) p).getRight();
    }

    /**
     * Places element e at the root of an empty tree and returns its new Position.
     *
     * @param e the new element
     * @return the Position of the new element
     * @throws IllegalStateException if the tree is not empty
     */
    public Position<E> addRoot(E e) throws IllegalStateException {
        // TODO
    	if (!isEmpty()) {
    		throw new IllegalStateException("Tree is not empty");
    	}
    	this.root = createNode(e, null, null, null);
    	this.size = 1;
    	return this.root;
    }

    public void insert(E e) {
        // TODO
    	Node<E> newNode = createNode(e, null, null, null);
    	
    	if(root == null) {
    		root = newNode;
    		size=1;
    		return;
    	}
    	
    	else {
    		addRecursive(root, e);
    	}
    }

    // recursively add Nodes to binary tree in proper position
    private Node<E> addRecursive(Node<E> p, E e) {
        // TODO
        if(p.getLeft() == null) {
        	Node<E> child = createNode(e, p, null, null);
        	p.setLeft(child);
        	size++;
        	return child;
        }
        
        if(p.getRight() == null) {
        	Node<E> child = createNode(e, p, null, null);
        	p.setRight(child);
        	size++;
        	return child;
        }
        
        Node<E> inserted = addRecursive(p.getLeft(), e);
        if(inserted != null) {
        	return inserted;
        }
        return addRecursive(p.getRight(), e);
    }

    /**
     * Creates a new left child of Position p storing element e and returns its
     * Position.
     *
     * @param p the Position to the left of which the new element is inserted
     * @param e the new element
     * @return the Position of the new element
     * @throws IllegalArgumentException if p is not a valid Position for this tree
     * @throws IllegalArgumentException if p already has a left child
     */
    public Position<E> addLeft(Position<E> p, E e) throws IllegalArgumentException {
        // TODO
    	Node<E> parent = validate(p);
    	if (parent.getLeft() != null) {
    		throw new IllegalStateException("p already has a left child");
    	}
    	Node<E> child = createNode(e, parent, null, null);
    	parent.setLeft(child);
    	this.size++;
    	return child;
    }

    /**
     * Creates a new right child of Position p storing element e and returns its
     * Position.
     *
     * @param p the Position to the right of which the new element is inserted
     * @param e the new element
     * @return the Position of the new element
     * @throws IllegalArgumentException if p is not a valid Position for this tree.
     * @throws IllegalArgumentException if p already has a right child
     */
    public Position<E> addRight(Position<E> p, E e) throws IllegalArgumentException {
        // TODO
    	Node<E> parent = validate(p);
    	if (parent.getRight() != null) {
    		throw new IllegalStateException("p already has a right child");
    	}
    	Node<E> child = createNode(e, parent, null, null);
    	parent.setRight(child);
    	this.size++;
    	return child;
    }

    /**
     * Replaces the element at Position p with element e and returns the replaced
     * element.
     *
     * @param p the relevant Position
     * @param e the new element
     * @return the replaced element
     * @throws IllegalArgumentException if p is not a valid Position for this tree.
     */
    public E set(Position<E> p, E e) throws IllegalArgumentException {
        // TODO
        Node<E> n = validate(p);
        E temp = n.getElement();
        n.setElement(e);
        return temp;
    }

    /**
     * Attaches trees t1 and t2, respectively, as the left and right subtree of the
     * leaf Position p. As a side effect, t1 and t2 are set to empty trees.
     *
     * @param p  a leaf of the tree
     * @param t1 an independent tree whose structure becomes the left child of p
     * @param t2 an independent tree whose structure becomes the right child of p
     * @throws IllegalArgumentException if p is not a valid Position for this tree
     * @throws IllegalArgumentException if p is not a leaf
     */
    public void attach(Position<E> p, LinkedBinaryTree<E> t1, LinkedBinaryTree<E> t2) throws IllegalArgumentException {
        // TODO
    	Node<E> n = validate(p);
    	
    	if(n.getLeft() != null || n.getRight() != null) {
    		throw new IllegalArgumentException("Position must be a leaf");
    	}
    	
    	if(!t1.isEmpty()) {
    		t1.root.setParent(n);
    		n.setLeft(t1.root);
    		size += t1.size;
    		t1.root = null;
    		t1.size = 0;
    	}
    	
    	if(!t2.isEmpty()) {
    		t2.root.setParent(n);
    		n.setRight(t2.root);
    		size += t2.size;
    		t2.root = null;
    		t2.size = 0;
    	}
    }

    /**
     * Removes the node at Position p and replaces it with its child, if any.
     *
     * @param p the relevant Position
     * @return element that was removed
     * @throws IllegalArgumentException if p is not a valid Position for this tree.
     * @throws IllegalArgumentException if p has two children.
     */
    public E remove(Position<E> p) throws IllegalArgumentException {
        // TODO
        Node<E> n = validate(p);
        
        if(n.getLeft() != null && n.getRight() != null) {
        	throw new IllegalArgumentException("Node has two children");
        }
        
        Node<E> child = (n.getLeft() != null ? n.getLeft() : n.getRight());
        
        if(child != null) {
        	child.setParent(n.getParent());
        }
        
        if(n == root) {
        	root = child;
        }
        else {
        	Node<E> parent = n.getParent();
        	if(n == parent.getLeft()) {
        		parent.setLeft(child);
        	}
        	else {
        		parent.setRight(child);
        	}
        }
        
        size--;
        E temp = n.getElement();
        
        n.setParent(n);
        n.setLeft(null);
        n.setRight(null);
        n.setElement(null);
        
        return temp;
    }

    public String toString() {
        return inorder().toString();
    }

    public void createLevelOrder(ArrayList<E> l) {
        // TODO
    	this.root = null;
    	this.size=0;
    	this.root = createLevelOrderHelper(l, null, 0);
    }

    private Node<E> createLevelOrderHelper(java.util.ArrayList<E> l, Node<E> p, int i) {
        // TODO
    	if(i < l.size() && l.get(i) != null) {  	
    	
	       	Node<E> n = createNode(l.get(i), p, null, null);
	       		       	
	        n.left = createLevelOrderHelper(l, n, 2*i+1);
	        n.right = createLevelOrderHelper(l, n, 2*i+2);
	        ++size;
	        
	        return n;
    	}
    	return null;
    }

    public void createLevelOrder(E[] arr) {
        this.root = null;
    	this.size = 0;
    	this.root = createLevelOrderHelper(arr, root, 0);
    }

    private Node<E> createLevelOrderHelper(E[] arr, Node<E> p, int i) {
        // TODO
    	if(i < arr.length && arr[i] != null) {
        	Node<E> n = createNode(arr[i], p, null, null);
        	
        	
        	n.left = createLevelOrderHelper(arr, n.left, 2*i+1);
        	n.right = createLevelOrderHelper(arr, n.right, 2*i+2);
        	++size;
        	return n;
        }
    	return p;
    }

    public String toBinaryTreeString() {
        BinaryTreePrinter<E> btp = new BinaryTreePrinter<>(this);
        return btp.print();
    }

    /**
     * Nested static class for a binary tree node.
     */
    public static class Node<E> implements Position<E> {
        private E element;
        private Node<E> left, right, parent;

        public Node(E e, Node<E> p, Node<E> l, Node<E> r) {
            element = e;
            left = l;
            right = r;
            parent = p;
        }

        // accessor
        public E getElement() {
            return element;
        }

        // modifiers
        public void setElement(E e) {
            element = e;
        }

        public Node<E> getLeft() {
            return left;
        }

        public void setLeft(Node<E> n) {
            left = n;
        }

        public Node<E> getRight() {
            return right;
        }

        public void setRight(Node<E> n) {
            right = n;
        }

        public Node<E> getParent() {
            return parent;
        }

        public void setParent(Node<E> n) {
            parent = n;
        }

        public String toString() {
            StringBuilder sb = new StringBuilder();
            if (element == null) {
                sb.append("\u29B0");
            } else {
                sb.append(element);
            }
            return sb.toString();
        }
    }
    
    
    public int countExternal(Position<E> p) {
    	if(isExternal(p)) {
    		return 1;
    	}
    	
    	int count=0;
    		
    	if(left(p) != null) {
    		count += countExternal(left(p));
    	}
    	
    	if(right(p) != null) {
    		count += countExternal(right(p));
    	}
    	
    	return count;
    }
    
    
    //LAB 4
    
    private int preIndex = 0;
    
    public void construct(E[] inorder, E[] preorder) {
    	if(inorder == null || preorder == null || inorder.length != preorder.length) {
    		root = null;
    		size = 0;
    		return;
    	}
    	

        root = constructHelperSimple(preorder, inorder, 0, inorder.length - 1, null);
        size = (inorder != null) ? inorder.length : 0;
    	
    }
    
    private Node<E> constructHelperSimple(E[] preorder, E[] inorder, int inStart, int inEnd, Node<E> parent) {
        
    	if (inStart > inEnd) {
        	return null;
        }


        E rootVal = preorder[preIndex++];
        Node<E> node = createNode(rootVal, parent, null, null);


        if (inStart == inEnd) {
        	return node;
        }

        int inIndex = inStart;
        while (inIndex <= inEnd && !inorder[inIndex].equals(rootVal)) {
            inIndex++;
        }

        
        node.left = constructHelperSimple(preorder, inorder, inStart, inIndex - 1, node);
        node.right = constructHelperSimple(preorder, inorder, inIndex + 1, inEnd, node);

        return node;
    }
 
    
    public List<List<E>> rootToLeafPaths() {
    	List<List<E>> paths = new ArrayList<>();
    	
    	if(root != null) {
    		rootToLeafHelper(root, new ArrayList<>(), paths);
    	}
    	
    	return paths;
    }
    
    private void rootToLeafHelper(Node<E> node, List<E> currentPath, List<List<E>> paths) {
    	
    	if(node == null) {
    		return;
    	}
    	
    	currentPath.add(node.getElement());
    	
    	if(node.getLeft() == null && node.getRight() == null) {
    		paths.add(new ArrayList<>(currentPath));
    	
    	}
    	else {
    		rootToLeafHelper(node.getLeft(), currentPath, paths);
    		rootToLeafHelper(node.getRight(), currentPath, paths);

    	}
    	
    	currentPath.remove(currentPath.size() -1);
    }
    
    public void printLeaves(Node<E> node) {
        if (node == null)
            return;

        if (node.left == null && node.right == null) {
            System.out.print(node.getElement() + " ");
        }

        printLeaves(node.left);
        printLeaves(node.right);
    }
    
}
