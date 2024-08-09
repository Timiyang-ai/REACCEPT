public static int dominance(final double[] u, final double[] v) {
		checkLength(u.length, v.length);

		return dominance(
			u, v, u.length,
			(a, b, i) -> Double.compare(a[i], b[i])
		);
	}