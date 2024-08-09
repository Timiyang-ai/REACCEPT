public default double distance(final int index, final Vec<T> other) {
		return distance().distance(index, data(), other.data());
	}