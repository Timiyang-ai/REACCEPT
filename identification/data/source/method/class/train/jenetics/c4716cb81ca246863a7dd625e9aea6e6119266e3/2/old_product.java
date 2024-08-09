public static double mse(final double[] calculated, final double[] expected) {
		if (expected.length != calculated.length) {
			throw new IllegalArgumentException(format(
				"Expected result and calculated results have different " +
					"length: %d != %d",
				expected.length, calculated.length
			));
		}

		double result = 0;
		for (int i = 0; i < expected.length; ++i) {
			result += (expected[i] - calculated[i])*(expected[i] - calculated[i]);
		}
		if (expected.length > 0) {
			result = result/expected.length;
		}

		return result;
	}