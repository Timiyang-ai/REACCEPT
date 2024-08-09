public Iterator<T> iterator () {
		if (iterable == null) iterable = new QueueIterable(this);
		return iterable.iterator();
	}