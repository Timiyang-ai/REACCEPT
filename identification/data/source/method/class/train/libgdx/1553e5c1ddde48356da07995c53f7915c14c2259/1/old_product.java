public T removeLast () {
		if (tail == null) {
			return null;
		}

		T payload = tail.payload;

		size--;

		Item<T> p = tail.prev;
		pool.free(tail);

		if (size == 0) {
			head = null;
			tail = null;
		} else {
			tail.next = null;
			tail = p;
		}


		return payload;
	}