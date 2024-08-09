<A> A[] toArray(final IntFunction<A[]> generator) {
		final A[] result = generator.apply(_size);
		copyTo(result);
		return result;
	}