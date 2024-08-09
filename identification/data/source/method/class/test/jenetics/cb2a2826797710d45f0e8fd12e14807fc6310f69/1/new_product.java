public default double distance(final Vec<T> other, final int index) {
		return distance().distance(data(), other.data(), index);
	}