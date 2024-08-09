@SuppressWarnings("unchecked")
	public static <C extends Comparable<? super C>> Histogram<C> of(
		final C... separators
	) {
		return new Histogram<C>(COMPARATOR, separators);
	}