package util;

import java.util.Iterator;

public class SimpleArray<T> implements Simple<T> {

	private T[] values;

	@SuppressWarnings("unchecked")
	public SimpleArray() {
		values = (T[]) new Object[0];
	}

	@SuppressWarnings("unchecked")
	@Override
	public Iterator<T> iterator() {

		return new ArrayIterator<T>(values);
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean add(T t) {
		try {
			T[] temp = values;
			values = (T[]) new Object[temp.length + 1];
			System.arraycopy(temp, 0, values, 0, temp.length);
			values[values.length - 1] = t;
			return true;
		} catch (ClassCastException ex) {
			ex.printStackTrace();
		}
		return false;
	}

	@SuppressWarnings("unchecked")
	@Override
	public void delete(int index) {
		try {
		T[] temp = values;
		values = (T[]) new Object[temp.length - 1];
		System.arraycopy(temp, 0, values, 0, index);
		int amountElementAfterIndex = temp.length - index - 1;
		System.arraycopy(temp, index + 1, values, index,amountElementAfterIndex );
		} catch (ClassCastException ex) {
			ex.printStackTrace();
		}
	}

	@Override
	public int size() {

		return values.length;
	}

	@Override
	public void update(int index, T t) {
		values[index] = t;

	}

	@Override
	public T get(int index) {

		return values[index];
	}
}
