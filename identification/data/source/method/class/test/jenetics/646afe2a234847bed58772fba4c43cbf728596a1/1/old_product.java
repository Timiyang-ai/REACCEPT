static double[] revert(final double[] probabilities) {
		final int N = probabilities.length;
		final int[] indexes = sort(probabilities);
		final double[] result = probabilities.clone();

		for (int i = 0; i < N; ++i) {
			result[indexes[N - i - 1]] = probabilities[indexes[i]];
		}

		return result;
	}