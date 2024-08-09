static double[] revert(final double[] probabilities) {
		final int N = probabilities.length;
		final int[] indexes = sort(probabilities);

        for (int i = 0, j = probabilities.length - 1; i < j; ++i, --j) {
            swap(probabilities, indexes, i, j);
        }
        return probabilities;
	}