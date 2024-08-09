public default int dominance(final Vec<T> other) {
		return dominance().compare(data(), other.data());
	}