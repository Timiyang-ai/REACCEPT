public static <T> int
	dominance(final T[] u, final T[] v, final Comparator<? super T> comparator) {
		requireNonNull(comparator);
		checkLength(u.length, v.length);

		return dominance(
			u, v, u.length, (a, b, i) -> comparator.compare(a[i], b[i])
		);
	}