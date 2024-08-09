public static int dominance(final long[] u, final long[] v) {
		checkLength(u.length, v.length);

		return dominance(
			u, v, u.length,
			(a, b, i) -> Long.compare(a[i], b[i])
		);
	}