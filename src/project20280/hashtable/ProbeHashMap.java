package project20280.hashtable;

import project20280.interfaces.Entry;

public class ProbeHashMap<K, V> extends AbstractHashMap<K, V> {
    private MapEntry<K, V>[] table;
    private final MapEntry<K, V> DEFUNCT = new MapEntry<>(null, null);

    public ProbeHashMap() {
        super();
    }

    /**
     * Creates a hash table with given capacity and prime factor 109345121.
     */
    public ProbeHashMap(int cap) {
        super(cap);
    }

    /**
     * Creates a hash table with the given capacity and prime factor.
     */
    public ProbeHashMap(int cap, int p) {
        super(cap, p);
    }

    @Override
    protected void createTable() {
        table = new MapEntry[capacity];
    }

    int findSlot(int h, K k) {
        // TODO
    	int firstDefunct = -1;
    	while(table[h] != null) {
    		if(table[h] == DEFUNCT) {
    			if(firstDefunct == -1) {
    				firstDefunct = h;
    			}
    			else if (table[h].getKey().equals(k)) {
    				return h;
    			}
    		}
        	h = (h+1) % capacity;
        }
    	
    	return (firstDefunct != -1) ? firstDefunct : h;
    }

    @Override
    protected V bucketGet(int h, K k) {
        // TODO
    	while(table[h] != null) {
    		if(table[h]!=DEFUNCT && table[h].getKey().equals(k)) {
    			return table[h].getValue();
    		}
    		
    		h = (h+1) % capacity;
    	}
    	
    	return null;
    }

    @Override
    protected V bucketPut(int h, K k, V v) {
        // TODO
    	int slot = findSlot(h, k);
                
        if(table[slot] == null || table[slot] == DEFUNCT) {
        	table[slot] = new MapEntry<>(k,v);
        	n++;
        	return null;
        }
        else {
        	V oldValue = table[slot].getValue();
        	table[slot].setValue(v);
        	return oldValue;
        }
    }

    @Override
    protected V bucketRemove(int h, K k) {
        // TODO
        while(table[h] != null) {
        	if(table[h] != DEFUNCT && table[h].getKey().equals(k)) {
        		V oldValue = table[h].getValue();
        		table[h] = DEFUNCT;
        		n--;
        		return oldValue;
        	}
        	h = (h+1) % capacity;
        }
        
        return null;
    }

    @Override
    public Iterable<Entry<K, V>> entrySet() {
        java.util.List<Entry<K,V>> buf = new java.util.ArrayList<>();
        for(int i=0; i<capacity; i++) {
        	if(table[i] != null && table[i] != DEFUNCT) {
        		buf.add(table[i]);
        	}
        }
        return buf;
    }
}
