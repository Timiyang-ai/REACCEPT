public T get (int index) {
		if (index < 0 || index >= size) {
			throw new NoSuchElementException("Index " + index + " does not exist, size is " + size);
		}
		final T[] values = this.values;

		int i = head + index;
		if (i >= values.length) {
			i -= values.length;
		}
		return values[i];
	}