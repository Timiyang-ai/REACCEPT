public T removeLast () {
		if (size == 0) {
			throw new NoSuchElementException("Queue is empty.");
		}

		final T[] values = this.values;
		int tail = this.tail;
		tail--;
		if (tail == -1) {
			tail = values.length - 1;
		}
		final T result = values[tail];
		values[tail] = null;
		this.tail = tail;
		size--;

		return result;
	}