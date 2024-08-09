public T pop (T defaultValue) {
		if (size == 0) {
			// Underflow
			return defaultValue;
		}

		final T[] values = this.values;

		final T result = values[head];
		values[head] = null;
		head++;
		if (head == values.length) {
			head = 0;
		}
		size--;

		return result;
	}