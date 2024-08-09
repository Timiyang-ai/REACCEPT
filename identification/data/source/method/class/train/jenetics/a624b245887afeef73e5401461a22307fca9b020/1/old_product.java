@SuppressWarnings("unchecked")
	public default T[] toArray(final T[] array) {
		if (array.length < length()) {
			final T[] copy = (T[])java.lang.reflect.Array.newInstance(
				array.getClass().getComponentType(), length()
			);
			for (int i = length(); --i >= 0;) {
				copy[i] = get(i);
			}

			return copy;
		}

		for (int i = 0, n = length(); i < n; ++i) {
			array[i] = get(i);
		}
		return array;
	}