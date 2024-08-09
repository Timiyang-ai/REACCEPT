public static <T> int
	dominance(final T[] u, final T[] v, final Comparator<? super T> comparator) {
		requireNonNull(comparator);
		checkLength(u.length, v.length);

		return dominance(
			u, v, u.length,
			(i, a, b) -> comparator.compare(a[i], b[i])
		);
	}