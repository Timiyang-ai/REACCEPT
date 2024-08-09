@SuppressWarnings("unchecked")
	public static <C extends Comparable<? super C>> Histogram<C> of(
		final C... separators
	) {
		return new Histogram<C>((o1, o2) -> o1.compareTo(o2), separators);
	}