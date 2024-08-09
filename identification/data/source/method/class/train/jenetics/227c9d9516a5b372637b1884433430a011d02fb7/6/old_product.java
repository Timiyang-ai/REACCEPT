public static <C extends Comparable<? super C>> Histogram<C> valueOf(
		final C... separators
	) {
		return new Histogram<C>(
				new Comparator<C>() {
					@Override public int compare(final C o1, final C o2) {
						return o1.compareTo(o2);
					}
				}, 
				separators
			);
	}