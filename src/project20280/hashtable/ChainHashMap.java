package project20280.hashtable;

import project20280.interfaces.Entry;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;
import java.io.File;

/*
 * Map implementation using hash table with separate chaining.
 */

public class ChainHashMap<K, V> extends AbstractHashMap<K, V> {
    // a fixed capacity array of UnsortedTableMap that serve as buckets
    private UnsortedTableMap<K, V>[] table; // initialized within createTable

    /**
     * Creates a hash table with capacity 11 and prime factor 109345121.
     */
    public ChainHashMap() {
        super();
    }

    /**
     * Creates a hash table with given capacity and prime factor 109345121.
     */
    public ChainHashMap(int cap) {
        super(cap);
    }

    /**
     * Creates a hash table with the given capacity and prime factor.
     */
    public ChainHashMap(int cap, int p) {
        super(cap, p);
    }

    /**
     * Creates an empty table having length equal to current capacity.
     */
    @Override
    @SuppressWarnings({"unchecked"})
    protected void createTable() {
        table = new UnsortedTableMap[capacity];
    }

    /**
     * Returns value associated with key k in bucket with hash value h. If no such
     * entry exists, returns null.
     *
     * @param h the hash value of the relevant bucket
     * @param k the key of interest
     * @return associate value (or null, if no such entry)
     */
    @Override
    protected V bucketGet(int h, K k) {
        // TODO
    	UnsortedTableMap<K, V> bucket = table[h];

        if (bucket == null) {
            return null;
        }

        return bucket.get(k);
    }

    /**
     * Associates key k with value v in bucket with hash value h, returning the
     * previously associated value, if any.
     *
     * @param h the hash value of the relevant bucket
     * @param k the key of interest
     * @param v the value to be associated
     * @return previous value associated with k (or null, if no such entry)
     */
    @Override
    protected V bucketPut(int h, K k, V v) {
        // TODO
    	UnsortedTableMap<K, V> bucket = table[h];

        if (bucket == null) {
            bucket = new UnsortedTableMap<>();
            table[h] = bucket;
        }

        int oldSize = bucket.size();
        V oldValue = bucket.put(k, v);

        if (bucket.size() > oldSize) { // new key inserted
            n++;
        }

        return oldValue;
    }


    /**
     * Removes entry having key k from bucket with hash value h, returning the
     * previously associated value, if found.
     *
     * @param h the hash value of the relevant bucket
     * @param k the key of interest
     * @return previous value associated with k (or null, if no such entry)
     */
    @Override
    protected V bucketRemove(int h, K k) {
        // TODO
    	UnsortedTableMap<K, V> bucket = table[h];

        if (bucket == null) {
            return null;
        }

        int oldSize = bucket.size();
        V removed = bucket.remove(k);

        if (bucket.size() < oldSize) { // something was removed
            n--;
        }

        return removed;
    }

    /**
     * Returns an iterable collection of all key-value entries of the map.
     *
     * @return iterable collection of the map's entries
     */
    @Override
    public Iterable<Entry<K, V>> entrySet() {
        /*
        for each element in (UnsortedTableMap []) table
            for each element in bucket:
                print element
        */
        ArrayList<Entry<K, V>> entries = new ArrayList<>();
        for (UnsortedTableMap<K, V> tm : table) {
            if (tm != null) {
                for (Entry<K, V> e : tm.entrySet()) {
                    entries.add(e);
                }
            }
        }
        return entries;
    }

    public String toString() {
        return entrySet().toString();
    }
    
    public static void countWordFreq(String file) throws FileNotFoundException {
    	  ChainHashMap<String, Integer> map = new ChainHashMap<>();
    	    Scanner sc = new Scanner(new File(file));

    	    // Count words
    	    while (sc.hasNext()) {
    	        String word = sc.next().toLowerCase();

    	        Integer count = map.get(word);

    	        if (count == null)
    	            map.put(word, 1);
    	        else
    	            map.put(word, count + 1);
    	    }

    	    sc.close();

    	    List<Entry<String, Integer>> list = new ArrayList<>();
    	    for (Entry<String, Integer> e : map.entrySet()) {
    	        list.add(e);
    	    }

    	    list.sort((a, b) -> b.getValue() - a.getValue());

    	    System.out.println("Top 10 words:");
    	    for (int i = 0; i < 10 && i < list.size(); i++) {
    	        System.out.println(list.get(i).getKey() + ": " + list.get(i).getValue());
    	    }
    }
    
    public static int countCollisions(String filename, int type, int param) throws FileNotFoundException {

        Scanner sc = new Scanner(new File(filename));
        HashMap<Integer, Integer> map = new HashMap<>();

        int collisions = 0;

        while (sc.hasNext()) {
            String word = sc.next();

            int h = 0;

            // Choose hash function
            if (type == 1) { // polynomial
                h = hashPoly(word, param);
            } else if (type == 2) { // cyclic shift
                h = hashCyclic(word, param);
            } else if (type == 3) { // old Java hash
                h = oldHash(word);
            }

            if (map.containsKey(h)) {
                collisions++;
                map.put(h, map.get(h) + 1);
            } else {
                map.put(h, 1);
            }
        }

        sc.close();
        return collisions;
    }
    
    public static int hashPoly(String s, int a) {
        int h = 0;
        for (int i = 0; i < s.length(); i++) {
            h = h * a + s.charAt(i);
        }
        return h;
    }
    
    public static int hashCyclic(String s, int shift) {
        int h = 0;
        for (int i = 0; i < s.length(); i++) {
            h = (h << shift) | (h >>> (32 - shift));
            h += s.charAt(i);
        }
        return h;
    }
    
    public static int oldHash(String s) {
        int hash = 0;
        int skip = Math.max(1, s.length() / 8);

        for (int i = 0; i < s.length(); i += skip) {
            hash = hash * 37 + s.charAt(i);
        }

        return hash;
    }

    public static void main(String[] args) throws FileNotFoundException {
        ChainHashMap<Integer, String> m = new ChainHashMap<Integer, String>();
        m.put(1, "One");
        m.put(10, "Ten");
        m.put(11, "Eleven");
        m.put(20, "Twenty");
 
        System.out.println("m: " + m);

        m.remove(11);
        System.out.println("m: " + m + "\n");
        
        countWordFreq("C:\\Users\\Anna\\OneDrive - University College Dublin\\UCD\\Stage 2 Spring\\Data Structures\\sample_text.txt");
        
        System.out.println();
        
        System.out.println("Poly (a=41): " + countCollisions("C:\\Users\\Anna\\OneDrive - University College Dublin\\UCD\\Stage 2 Spring\\Data Structures\\words.txt", 1, 41));
        System.out.println("Poly (a=17): " + countCollisions("C:\\Users\\Anna\\OneDrive - University College Dublin\\UCD\\Stage 2 Spring\\Data Structures\\words.txt", 1, 17));

        System.out.println("Cyclic (shift=7): " + countCollisions("C:\\Users\\Anna\\OneDrive - University College Dublin\\UCD\\Stage 2 Spring\\Data Structures\\words.txt", 2, 7));

        int bestShift = 0;
        int min = Integer.MAX_VALUE;

        for (int i = 0; i <= 31; i++) {
            int c = countCollisions("C:\\Users\\Anna\\OneDrive - University College Dublin\\UCD\\Stage 2 Spring\\Data Structures\\words.txt", 2, i);
            if (c < min) {
                min = c;
                bestShift = i;
            }
        }

        System.out.println("Best shift: " + bestShift + " with " + min + " collisions");

        System.out.println("Old hash: " + countCollisions("C:\\Users\\Anna\\OneDrive - University College Dublin\\UCD\\Stage 2 Spring\\Data Structures\\words.txt", 3, 0));
    }
    
    
    
}
