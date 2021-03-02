package hr.fer.zemris.java.custom.collections;

import java.util.Objects;

/**
 * {@code Dictionary} is a class which represents a dictionary structure.
 * Each element is represented by it's {@code value} of type {@code <V>} and {@code key} of type{@code <K>}. 
 * General contract is that each {@code key} must be unique. {@code null} cannot be a key.
 * This class effectively adapts the ArrayIndexedCollection class.
 * 
 * @author Božidar Grgur Drmić
 *
 * @param <K> Type of {@code key}
 * @param <V> Type of {@code value}
 */
public class Dictionary<K, V> {

	/**
	 * Entry is a private class whose instances represent
	 * one element stored in {@code dictionary}.
	 */
	private class Entry {
		private K key;
		private V value;
		
		private Entry() {
			super();
		}
		
		private Entry(K key, V value) {
			Objects.requireNonNull(key);
			
			this.key = key;
			this.value = value;
		}
	}
	
	/**
	 * A variable which represents the {@code dictionary} structure.
	 */
	private ArrayIndexedCollection<Entry> dictionary;
	
	/**
	 * A public constructor which initialises the {@code dictionary} variable.
	 */
	public Dictionary() {
		this.dictionary = new ArrayIndexedCollection<Entry>();
	}
	
	/**
	 * A private method which returns the index of an element with some {@code key}.
	 * If there is no such element {@literal -1} is returned.
	 * 
	 * @param key The {@code key} of the object.
	 * @return Returns the index or -1 if there's no such element.
	 */
	private int indexOfKey(Object key) {
		for(int i = 0; i < dictionary.size(); i++) {
			if(!dictionary.get(i).key.equals(key)) continue;
			return i;
		}
		return -1;
	}
	
	/**
	 * Returns true if dictionary has no elements.
	 * 
	 * @return Returns {@code true} only if dictionary is empty. {@code False} otherwise.
	 */
	public boolean isEmpty() {
		return dictionary.isEmpty();
	}
	
	/**
	 * Returns the size of dictionary.
	 * @return Returns the number of elements stored in dictionary.
	 */
	public int size() {
		return dictionary.size();
	}
	
	/**
	 * Puts an elements into the dictionary. If there already was an element
	 * with that {@code key}, than it's {@code value} is overrided.
	 * 
	 * @param key A {@code key} of the new element.
	 * @param value A {@code value} of the new element.
	 */
	public void put(K key, V value) {
		if(key == null) return;
		
		int index = indexOfKey(key);
		if(index != -1) {
			dictionary.get(index).value = value;
			return;
		}
		
		dictionary.add(new Entry(key, value));
	}
	
	/**
	 * Gets the {@code value} of an element with some {@code key}.
	 * If there is no such element {@code null} is returned.
	 * 
	 * @param key The {@code key} of an element.
	 * @return Returns {@code value} or {@code null} if there's no such element.
	 */
	public V get(Object key) {
		if(key == null) return null;
		
		int index = indexOfKey(key);
		if(index == -1) return null;
		
		return dictionary.get(index).value;
	}
	
	/**
	 * Removes all elements from dictionary.
	 */
	public void clear() {
		dictionary.clear();
	}
	
}
