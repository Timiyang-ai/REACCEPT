static <T> void crossover(
		final MSeq<T> that,
		final MSeq<T> other,
		final int[] indexes
	) {

		for (int i = 0; i < indexes.length - 1; i += 2) {
			final int start = indexes[i];
			final int end = indexes[i + 1];
			that.swap(start, end, other, start);
		}
		if (indexes.length%2 == 1) {
			final int index = indexes[indexes.length - 1];
			that.swap(index, min(that.length(), other.length()), other, index);
		}
	}