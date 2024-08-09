static double[] revert(final double[] array) {
		final int[] indexes = sort(array);

		for (int i = 0, j = array.length - 1; i < j; ++i, --j) {
			swap(array, indexes[i], indexes[j]);
			swap(indexes, i, j);
		}
		return array;
	}