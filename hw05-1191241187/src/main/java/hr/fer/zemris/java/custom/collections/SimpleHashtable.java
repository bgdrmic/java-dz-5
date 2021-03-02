package hr.fer.zemris.java.custom.collections;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

/**
 * SimpleHashTable is a class which implements {@code Iterable} represents an
 * implementation of a map represented by a table of dispersed storage.
 * It stores ordered pairs in form (K, V) where K is a non-null unique key
 * of type {@code <K>} and V is of type {@code <V>}. It allows two public constructors.
 * One which sets the table size to the default size of {@literal 16} and the other which
 * accepts the initial table size as an argument and sets the table's size to the first
 * power of two equal or greater than that number.
 * 
 * @author Božidar Grgur Drmić
 *
 * @param <K> Type for keys.
 * @param <V> Type for values.
 */
public class SimpleHashtable<K,V> implements Iterable<SimpleHashtable.TableEntry<K,V>>{
	
	/**
	 * An {@code Iterator} for this class.
	 */
	private class IteratorImpl implements Iterator<SimpleHashtable.TableEntry<K,V>> {
		
		/**
		 * A cursor to the current slot of table.
		 */
		private int index;
		/**
		 * The last element that has been read.
		 */
		private TableEntry<K,V> previousElement;
		/**
		 * The next element that yet has to be read.
		 */
		private TableEntry<K,V> nextElement;
		/**
		 * modification count of the object that is being iterated.
		 */
		private long savedModificationCount;
		
		/**
		 * A constructor for the {@code IteratorImpl} class.
		 */
		public IteratorImpl() {
			index = -1;
			while(nextElement == null && ++index < table.length) {
				nextElement = table[index];
			}
			savedModificationCount = modificationCount;
		}
		
		@Override
		public boolean hasNext() {
			if(savedModificationCount != modificationCount) {
				throw new ConcurrentModificationException();
			}
			return nextElement != null;
		}
		
		@Override
		public SimpleHashtable.TableEntry<K,V> next() {
			if(!hasNext()) {
				throw new NoSuchElementException();
			}
			
			previousElement = nextElement;
			nextElement = nextElement.next;
			
			while(nextElement == null && ++index < table.length) {
				nextElement = table[index];
			}
			
			return previousElement;
		}
		
		@Override
		public void remove() {
			if(previousElement == null) {
				throw new IllegalStateException();
			}
			if(savedModificationCount != modificationCount) {
				throw new ConcurrentModificationException();
			}
			
			SimpleHashtable.this.remove(previousElement.getKey());
			savedModificationCount = modificationCount;
			previousElement = null;
		}
	}
	
	/**
	 * {@code TableEntry<K,V>} is a class which represents one entry in this structure.
	 *
	 * @param <K> Type for key .
	 * @param <V> Type for value.
	 */
	public static class TableEntry<K,V> {
		/**
		 * The {@code key} variable of an entry;
		 */
		private K key;
		/**
		 * The {@code value} variable of an entry;
		 */
		private V value;
		/**
		 * Pointer to the next entry in the same slot.
		 */
		private TableEntry<K,V> next;
		
		/**
		 * A public constructor for this class.
		 * It creates a new instance of this class with the {@code key}
		 * and {@code value} variables set to some values.
		 * @param key - the value {@code key} is set to.
		 * @param value - the value {@code value} is set to.
		 */
		public TableEntry(K key, V value) {
			super();
			this.key = key;
			this.value = value;
		}
		
		/**
		 * A getter method for {@code value} variable.
		 * @return Returns the {@code value} variable.
		 */
		public V getValue() {
			return value;
		}
		/**
		 * A setter for {@code value} variable.
		 * @param value - {@code value} variable is set to this value.
		 */
		public void setValue(V value) {
			this.value = value;
		}
		/**
		 * A getter method for {@code key} variable.
		 * @return Returns the {@code key} variable.
		 */
		public K getKey() {
			return key;
		}

		@Override
		public int hashCode() {
			return ((key == null) ? 0 : key.hashCode());
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			@SuppressWarnings("rawtypes")
			TableEntry other = (TableEntry) obj;
			if (key == null) {
				if (other.key != null)
					return false;
			} else if (!key.equals(other.key))
				return false;
			return true;
		}
		
	}
	
	/**
	 * The default number of slots in the table.
	 */
	private static final int DEFAULT_CAPACITY = 16;
	
	/**
	 * Current modification count. It is changed every time some change is made.
	 */
	private  int modificationCount;
	
	/**
	 * Current number of entries in the hash table.
	 */
	private int size;
	
	/**
	 * An array in which the entries are stored.
	 */
	private TableEntry<K,V>[] table;
	
	/**
	 * The number of used slots.
	 */
	private int slotsUsed;
	
	/**
	 * The default constructor for {@code SimpleHashTable} class. It sets the {@code table} capacity
	 * to the {@code DEFAULT_CAPACITY}.
	 */
	public SimpleHashtable() {
		this(DEFAULT_CAPACITY);
	}
	
	/**
	 * A constructor for {@code SimpleHashTable} class. It sets the {@code table} capacity
	 * to some value.
	 * 
	 * @param initialCapacity - the capacity of the {@code table} 
	 */
	@SuppressWarnings("unchecked")
	public SimpleHashtable(int initialCapacity) {
		if(initialCapacity < 1) {
			throw new IllegalArgumentException();
		}
		
		int capacity = 1;
		while(capacity < initialCapacity) {
			capacity *= 2;
		}
		
		table = (TableEntry<K,V>[]) new TableEntry[capacity];
	}
	
	/**
	 * A getter method for {@code size} variable.
	 * @return Returns the {@code size}
	 */
	public int size() {
		return size;
	}
	
	/**
	 * A method which determines whether this hash table is empty.
	 * @return Returns true only if there are no entries in this table.
	 */
	public boolean isEmpty() {
		return size == 0;
	}
	
	/**
	 * A private method which finds the entry with some {@code key}.
	 * Returns {@code null} if there are no such entries.
	 * @param key - the {@code key} of an {@code entry} which is searched for. 
	 * @return Returns the entry with that {@code key} or {@code null} if there is
	 * 		   no such an {@code entry}.
	 */
	private TableEntry<K,V> locationOfKey(Object key) {
		if(key == null) return null;		
		
		for(TableEntry<K,V> entry : this) {
			if(entry.key.equals(key)) return entry;	
		}
		
		return null;
	}
	
	/**
	 * A method which determines whether there is an {@code entry} with some {@code key}.
	 * 
	 * @param key - the {@code key} of an {@code entry} which is searched for. 
	 * @return Returns true only if there is such an {@code entry}. False otherwise.
	 */
	public boolean containsKey(Object key) {
		return locationOfKey(key) != null;
	}
	
	/**
	 * A method which determines whether there is an {@code entry} with some {@code value}.
	 * 
	 * @param value - the {@code value} of an {@code entry} which is searched for.
	 * @return Returns true only if there is such an {@code entry}. False otherwise.
	 */
	public boolean containsValue(Object value) {
		for(TableEntry<K,V> entry : this) {
			if(value == null && entry.value == null) return true;
			if(entry.value != null && entry.value.equals(value)) return true;	
		}
		
		return false;
	}
	
	/**
	 * A private method which returns the {@code value} of an {@code entry} with some {@code key}.
	 * Returns {@code null} if there are no such entries.
	 * @param key - the {@code key} of an {@code entry} which is searched for. 
	 * @return Returns the {@code value} of an {@code entry} with some {@code key}.
	 *         Returns {@code null} if there are no such entries.
	 */
	public V get(Object key) {
		var entry = locationOfKey(key);
		
		if(entry == null) return null;
		return entry.getValue();		
	}
	
	/**
	 * A method which creates a new entry with some {@code key} and {@code value} and puts it into the hash table.
	 * If an {@code entry} with that {@code key} already exists, it just changes it's {@code value}. 
	 * If more than three quarters of all the slots are used it doubles the capacity of the {@code table}.
	 * It throws NullPointerException if {@code key} is {@code null}.
	 * 
	 * @param key - {@code key} of the entry.
	 * @param value - {@code value} of the entry.
	 * @throws NullPointerException if {@code key} is {@code null}.
	 */
	public void put(K key, V value) {
		Objects.requireNonNull(key);
		var entry = locationOfKey(key);
		
		modificationCount++;
		
		if(entry != null) {
			entry.setValue(value);
			return;
		}
		
		size++;
		entry = new TableEntry<>(key, value);
		int index = Math.abs(entry.hashCode()) % table.length;
		
		if(table[index] == null) {
			table[index] = entry;
			return;
		}
		
		slotsUsed++;
		var lastEntry = table[index];
		while(lastEntry.next != null) {
			lastEntry = lastEntry.next;
		}
		
		lastEntry.next = entry;
		if(slotsUsed > 0.75 * table.length) {
			expandTable();
		}
	}
	
	/**
	 * A method which removes an entry with some {@code key} from the hash table.
	 * 
	 * @param key - {@code key} of the entry.
	 */
	public void remove(Object key) {
		var entry = locationOfKey(key);
		if(entry == null) return;
		
		modificationCount++;
		size--;
		
		int index = Math.abs(entry.hashCode()) % table.length;
		
		if(table[index] == entry) {
			table[index] = entry.next;
			return;
		}
		
		var previousEntry = table[index];
		while(previousEntry.next != entry) {
			previousEntry = previousEntry.next;
		}
		
		previousEntry.next = entry.next;
	}
	
	@Override
	public String toString() {
		if(isEmpty()) return "[]";
		
		String result = "";
		for(TableEntry<K,V> entry : this) {
			result += entry.getKey() + "=" + entry.getValue() + ", ";
		}
			
		return "[" + result.substring(0, result.length()-2) + "]";
	}
	
	/**
	 * A private method which creates a new array of {@code tableEntry} with double the size
	 * of the current one, with all the existing entries copied into it. 
	 */
	@SuppressWarnings("unchecked")
	private void expandTable() {
		var oldTable = table;
		table = (TableEntry<K,V>[]) new TableEntry[2 * table.length];
		size = 0;
		slotsUsed = 0;
		modificationCount++;
		
		for(int i = 0; i < oldTable.length; i++) {
			var entry  = oldTable[i];
			while(entry != null) {
				put(entry.getKey(), entry.getValue());
				entry = entry.next;
			}
			oldTable[i] = null;
		}		
	}
	
	@SuppressWarnings("unused")
	private void clear() {
		modificationCount++;
		
		for(int i = 0; i < table.length; i++) {
			table[i] = null;
		}
	}

	@Override
	public Iterator<TableEntry<K,V>> iterator() {
		return new IteratorImpl();
	}
}
