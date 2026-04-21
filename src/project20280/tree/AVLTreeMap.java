package project20280.tree;

import project20280.interfaces.Entry;
import project20280.interfaces.Position;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * An implementation of a sorted map using an AVL tree.
 */

public class AVLTreeMap<K, V> extends TreeMap<K, V> {

    /**
     * Constructs an empty map using the natural ordering of keys.
     */
    public AVLTreeMap() {
        super();
    }

    /**
     * Constructs an empty map using the given comparator to order keys.
     *
     * @param comp comparator defining the order of keys in the map
     */
    public AVLTreeMap(Comparator<K> comp) {
        super(comp);
    }

    /**
     * Returns the height of the given tree position.
     */
    protected int height(Position<Entry<K, V>> p) {
        // TODO
    	if (p == null || isExternal(p)) {
            return 0;
        }
        return tree.getAux(p);
    }

    /**
     * Recomputes the height of the given position based on its children's heights.
     */
    protected void recomputeHeight(Position<Entry<K, V>> p) {
        // TODO
    	if (isExternal(p)) {
            tree.setAux(p, 0);
            return;
        }
        int h = 1 + Math.max(height(left(p)), height(right(p)));
        tree.setAux(p, h);
    }

    /**
     * Returns whether a position has balance factor between -1 and 1 inclusive.
     */
    protected boolean isBalanced(Position<Entry<K, V>> p) {
        // TODO
    	int balance = height(left(p)) - height(right(p));
        return Math.abs(balance) <= 1;
    }

    /**
     * Returns a child of p with height no smaller than that of the other child.
     */
    protected Position<Entry<K, V>> tallerChild(Position<Entry<K, V>> p) {
    	int leftHeight = height(left(p));
        int rightHeight = height(right(p));

        if (leftHeight > rightHeight) {
            return left(p);
        }

        if (leftHeight < rightHeight) {
            return right(p);
        }

        // Tie-breaker
        if (p == root()) {
            return left(p);
        }

        if (p == left(parent(p))) {
            return left(p);
        } else {
            return right(p);
        }
        
    }

    /**
     * Utility used to rebalance after an insert or removal operation. This
     * traverses the path upward from p, performing a trinode restructuring when
     * imbalance is found, continuing until balance is restored.
     */
    protected void rebalance(Position<Entry<K, V>> p) {
        // TODO
    	while (p != null) {

            if (isExternal(p)) {
                p = parent(p);
                continue;
            }

            int oldHeight = height(p);

            if (!isBalanced(p)) {

                // Find x, y, z
                Position<Entry<K, V>> z = p;
                Position<Entry<K, V>> y = tallerChild(z);
                Position<Entry<K, V>> x = tallerChild(y);

                p = tree.restructure(x);

                recomputeHeight(left(p));
                recomputeHeight(right(p));
                recomputeHeight(p);
            } else {
                recomputeHeight(p);
            }

            if (height(p) == oldHeight) {
                break;
            }

            p = parent(p);
        }
    }

    /**
     * Overrides the TreeMap rebalancing hook that is called after an insertion.
     */
    @Override
    protected void rebalanceInsert(Position<Entry<K, V>> p) {
    	tree.setAux(p, 1);
        rebalance(parent(p));
    }

    /**
     * Overrides the TreeMap rebalancing hook that is called after a deletion.
     */
    @Override
    protected void rebalanceDelete(Position<Entry<K, V>> p) {
        // TODO
    	if (p != null) {
            rebalance(p);
        }
    }


    
    /**
     * Ensure that current tree structure is valid AVL (for debug use only).
     */
    private boolean sanityCheck() {
        for (Position<Entry<K, V>> p : tree.positions()) {
            if (isInternal(p)) {
                if (p.getElement() == null)
                    System.out.println("VIOLATION: Internal node has null entry");
                else if (height(p) != 1 + Math.max(height(left(p)), height(right(p)))) {
                    System.out.println("VIOLATION: AVL unbalanced node with key " + p.getElement().getKey());
                    dump();
                    return false;
                }
            }
        }
        return true;
    }

    public String toBinaryTreeString() {
        BinaryTreePrinter<Entry<K, V>> btp = new BinaryTreePrinter<>(this.tree);
        return btp.print();
    }
    
    @Override
    public String toString() {
        
        return tree.toString();
    }
    
    @Override
    public Iterable<Entry<K, V>> subMap(K fromKey, K toKey) throws IllegalArgumentException {
        checkKey(fromKey);
        checkKey(toKey);
        ArrayList<Entry<K, V>> buffer = new ArrayList<>();

        for (Position<Entry<K, V>> p : tree.inorder()) {
            if (isInternal(p)) {
                K k = p.getElement().getKey();
                if (compare(k, fromKey) >= 0 && compare(k, toKey) < 0) {
                    buffer.add(p.getElement());
                }
            }
        }

        return new Iterable<Entry<K, V>>() {
            private final ArrayList<Entry<K, V>> entries = buffer;

            @Override
            public java.util.Iterator<Entry<K, V>> iterator() {
                return entries.iterator();
            }

            @Override
            public String toString() {
                StringBuilder sb = new StringBuilder("[");
                boolean first = true;
                for (Entry<K, V> e : entries) {
                    if (!first) sb.append(", ");
                    first = false;
                    sb.append(e.getKey());
                }
                sb.append("]");
                return sb.toString();
            }
        };
    }
    
  
    public static void main(String[] args) {
        AVLTreeMap avl = new AVLTreeMap<>();

        Integer[] arr = new Integer[]{5, 3, 10, 2, 4, 7, 11, 1, 6, 9, 12, 8};

        for (Integer i : arr) {
            if (i != null) avl.put(i, i);
            System.out.println("root " + avl.root());
        }
        System.out.println(avl.toBinaryTreeString());

        avl.remove(5);
        System.out.println(avl.toBinaryTreeString());

    }
}
