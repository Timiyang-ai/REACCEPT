public static int dominance(final int[] u, final int[] v) {
		checkLength(u.length, v.length);

		return dominance(
			u, v, (a, b, i) -> Integer.compare(a[i], b[i]), u.length
		);
	}