/*
 * Name: Adam Mohr
 * Student ID: 040669681
 * Course & Section: CST8132 301
 * Assignment: Lab 7/8
 * Date: Nov 19, 2018
 */

package cst8132.sarray;

/**
 * Use Java generics to store any generic type in an array, rather than just
 * String object previously.
 * 
 * @author Adam Mohr
 * @version 1.1
 * @param <T> generic type
 * @since 1.8
 */
public class GenericArray<T> {

	private int capacity; // Current capacity of array.
	private int size; // Current size of array.

	private Object[] tArray; // Generic array of Objects.

	/**
	 * Default constructor, instantiates an empty GenericgArray with an initial
	 * capacity of ten.
	 */
	public GenericArray() {
		this(10);
	}

	/**
	 * Initial constructor, instantiates a GenericArray with desired capacity.
	 * 
	 * @param initialCapacity constructs an empty GenericArray with the specified
	 *                        capacity.
	 * 
	 * @throws IllegalArgumentException if the specified initial capacity is
	 *                                  negative.
	 */
	public GenericArray(int initialCapacity) throws IllegalArgumentException {
		if (initialCapacity < 0)
			throw new IllegalArgumentException();

		size = 0;
		capacity = initialCapacity;

		tArray = new Object[initialCapacity];
	}

	/**
	 * Copy constructor, instantiates a GenericArray that is a copy of a given
	 * GenericArray.
	 * 
	 * @param ga GenericArray object.
	 * @throws NullPointerException if copy is null.
	 */
	public GenericArray(GenericArray<T> ga) throws NullPointerException {
		if (ga == null) {
			throw new NullPointerException();
		}

		System.arraycopy(ga.tArray, 0, tArray, 0, size);

		this.size = ga.size();

		this.capacity = ga.capacity();

		// tArray = new Object Capacity();
		// Can't get this to work but I think I need it for a deep copy constructor...

	}

	/**
	 * Append the specified Object to the end of this GenericArray. Increases
	 * capacity if needed.
	 * 
	 * @param t Object to add.
	 * @throws NullPointerException if Object t is null.
	 */
	public void add(T t) throws NullPointerException {
		if (t == null) {
			throw new NullPointerException();
		}

		if (this.size() >= this.capacity()) {
			ensureCapacity(capacity() + 1);

		}
		tArray[this.size()] = t;
		size++;
	}

	/**
	 * Insert the specified Object at the specified position in this GenericArray.
	 * Increases capacity if needed.
	 * 
	 * @param index position to insert at.
	 * @param t     Object to add.
	 * @throws IndexOutOfBoundsException if index is less than 0 or greater than
	 *                                   size.
	 * @throws NullPointerException      if Object t is null.
	 */
	public void add(int index, T t) throws NullPointerException, IndexOutOfBoundsException {
		if (t == null)
			throw new NullPointerException();

		if (index < 0 || index > size)
			throw new IndexOutOfBoundsException();

		if (size == capacity)
			ensureCapacity(capacity + capacity / 2);

		for (int i = size; i > index; i--) {
			tArray[i] = tArray[i - 1];
		}

		tArray[index] = t;
		size++;
	}

	/**
	 * Return the capacity of this GenericArray.
	 * 
	 * @return capacity length of this GenericArray.
	 */
	public int capacity() {
		return capacity;
	}

	/**
	 * Remove all of the Objects from this GenericArray.
	 */
	public void clear() {
		for (; size > 0; size--)
			tArray[size - 1] = null;
	}

	/**
	 * Return true if this GenericArray contains the specified Object.
	 * 
	 * @param t Object to check for.
	 * @return true if this GenericArray contains the specified Object.
	 * @throws NullPointerException if Object t is null.
	 */
	public boolean contains(T t) throws NullPointerException {
		if (t == null) {
			throw new NullPointerException();
		}

		return indexOf(t) > -1;
	}

	/**
	 * Ensure that it can hold at least the number of Objects specified by the
	 * minimum capacity argument.
	 * 
	 * @param minCapacity required in this GenericArray.
	 * @throws CapacityOutOfBoundsException if specified capacity is less than or
	 *                                      equal to the GenericArray size.
	 */
	public void ensureCapacity(int minCapacity) throws CapacityOutOfBoundsException {
		if (minCapacity <= size)
			throw new CapacityOutOfBoundsException();

		if (capacity < minCapacity) {
			capacity = minCapacity;

			Object[] temp = new Object[minCapacity];

			for (int i = 0; i < size; i++) {
				temp[i] = tArray[i];
			}
			tArray = temp;
		}
	}

	/**
	 * Return the index of the first occurrence of the specified Object in this
	 * GenericArray, or null if this GenericArray does not contain the Object.
	 * 
	 * @param index of specified Object if it exists.
	 * @return index if Object is present or null if it is not.
	 * @throws IndexOutOfBoundsException if index is less than 0 or greater than
	 *                                   size of GenericArray.
	 */
	public T get(int index) throws IndexOutOfBoundsException {
		if (index >= size || index < 0) {
			throw new IndexOutOfBoundsException();
		}

		return (T) tArray[index];
	}

	/**
	 * Return the index of the first occurrence of the specified Object in this
	 * GenericArray, or -1 if this GenericArray does not contain the Object.
	 * 
	 * @param t Object to check for
	 * @return index if Object occurs or -1 if it does not.
	 * @throws NullPointerException is Object is null.
	 */
	public int indexOf(T t) throws NullPointerException {
		if (t == null)
			throw new NullPointerException();

		int found = -1;

		for (int i = 0; i < size; i++) {
			if (t.equals(tArray[i])) {
				found = i;
				break;
			}
		}
		return found;
	}

	/**
	 * Check to see if GenericArray is currently empty.
	 * 
	 * @return true if this GenericArray contains no Objects, otherwise false
	 */
	public boolean isEmpty() {
		return size == 0;
	}

	/**
	 * Remove the Object at the specified position in this GenericArray.
	 * 
	 * @param index position to remove at.
	 * @return Object previously at the specified position.
	 * @throws IndexOutOfBoundsException if index is less than 0 or greater than
	 *                                   size of GenericArray.
	 */
	public T remove(int index) throws IndexOutOfBoundsException {
		if (index >= size || index < 0) {
			throw new IndexOutOfBoundsException();
		}

		T oldObject = (T) tArray[index];

		for (; index < size; index++) {
			tArray[index] = tArray[index + 1];
		}
		size--;
		return oldObject;
	}

	/**
	 * Remove the first occurrence of the specified Object from this GenericArray,
	 * if it is present.
	 * 
	 * @param t Object to check for.
	 * @return true if this GenericArray contained the specified Object.
	 * @throws NullPointerException if Object is null.
	 */
	public boolean remove(T t) throws NullPointerException {
		if (t == null) {
			throw new NullPointerException();
		}

		int index = indexOf(t);
		remove(index);
		return index > -1;
	}

	/**
	 * Replace the Object at the specified position in this GenericArray with the
	 * specified Object. Increases capacity if needed.
	 * 
	 * @param index position in GenericArray.
	 * @param t     Object to set.
	 * @return Object previously at the specified position.
	 * @throws IndexOutOfBoundsException if index is less than 0 or greater than
	 *                                   size of GenericArray.
	 * @throws NullPointerException      if Object is null.
	 */
	public T set(int index, T t) throws IndexOutOfBoundsException, NullPointerException {
		if (index < 0 || index > size)
			throw new IndexOutOfBoundsException();

		if (t == null)
			throw new NullPointerException();

		T oldObject;

		if (index > capacity) {
			ensureCapacity(index + 1);
			oldObject = null;
		} else {
			oldObject = (T) tArray[index];
		}

		if (index >= size) {
			size = index + 1;
		}

		tArray[index] = t;
		return oldObject;
	}

	/**
	 * Check size of this GenericArray.
	 * 
	 * @return size of this GenericArray.
	 */
	public int size() {
		return size;
	}

}
