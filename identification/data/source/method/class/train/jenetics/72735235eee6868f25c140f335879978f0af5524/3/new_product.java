public void addAll(final Iterable<? extends T> values) {
		if (values instanceof Buffer) {
			final Object[] array = ((Buffer<?>)values).toArray();
			addAll(array, 0, array.length);
		} else if (values instanceof Collection) {
			final Object[] array = ((Collection<?>)values).toArray();
			addAll(array, 0, array.length);
		} else {
			for (T value : values) {
				add(value);
			}
		}
	}