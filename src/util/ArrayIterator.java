package util;

import java.util.Iterator;

@SuppressWarnings("rawtypes")
public class ArrayIterator<T> implements Iterator {
	
	private int index = 0;
	private T[] values;

	ArrayIterator(T[] values) {
		this.values = values;
	}

	@Override
	public boolean hasNext() {

		return index < values.length;
	}

	@Override
	public T next() {

		return values[index++];
	}

}
