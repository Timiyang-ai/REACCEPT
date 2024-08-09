@SuppressWarnings("unchecked")
	public default <B> B[] toArray(final B[] array) {
		if (array.length < length()) {
			final Object[] copy = (Object[])java.lang.reflect.Array
				.newInstance(array.getClass().getComponentType(), length());

			for (int i = length(); --i >= 0;) {
				copy[i] = get(i);
			}

			return (B[])copy;
		}

		for (int i = 0, n = length(); i < n; ++i) {
			((Object[])array)[i] = get(i);
		}
		if (array.length > length()) {
			array[length()] = null;
		}

		return array;
	}