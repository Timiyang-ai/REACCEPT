@SuppressWarnings("unchecked")
	public static <C extends Comparable<? super C>> Histogram<C> valueOf(
		final C... separators
	) {
		return new Histogram<>(COMPARATOR, separators);
	}