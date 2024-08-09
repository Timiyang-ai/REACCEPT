public static int dominance(final int[] u, final int[] v) {
		checkLength(u.length, v.length);

		return dominance(
			u, v, u.length,
			(i, a, b) -> Integer.compare(a[i], b[i])
		);
	}