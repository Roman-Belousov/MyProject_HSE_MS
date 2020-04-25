package util;

import java.util.ArrayList;

public class Map<K, V> {
	private ArrayList<K> keys = new ArrayList<>();
	private ArrayList<V> values = new ArrayList<>();

	public void put(K key, V value) {
		boolean exists = false;
		int i;
		for(i = 0; i < keys.size(); i++) {
			if(keys.get(i).equals(key)) {
				exists = true;
				break;
			}
		}
		if(exists) {
			values.set(i, value);
		} else {
			keys.add(key);
			values.add(value);
		}
	}

	public V get(K key) {
		for(int i = 0; i < keys.size(); i++) {
			if(keys.get(i).equals(key)) {
				return values.get(i);
			}
		}
		return null;
	}

	public ArrayList<K> getKeys() {
		return keys;
	}
}


