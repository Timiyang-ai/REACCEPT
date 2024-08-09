public Iterator<T> iterator () {
		if (Collections.allocateIterators) return new QueueIterator(this, true);
		if (iterable == null) iterable = new QueueIterable(this);
		return iterable.iterator();
	}