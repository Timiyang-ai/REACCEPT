public T remove (T defaultValue) {
		if (size == 0) {
			// Underflow
			return defaultValue;
		}

		final T[] values = this.values;

		T result = values[head];
		values[head] = null;
		head++;
		if (head == values.length) {
			head = 0;
		}
		size--;

		return result;
	}