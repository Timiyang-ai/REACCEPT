public static int dominance(final long[] u, final long[] v) {
		checkLength(u.length, v.length);

		return dominance(
			u, v, u.length,
			(i, a, b) -> Long.compare(a[i], b[i])
		);
	}