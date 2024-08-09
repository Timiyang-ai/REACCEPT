static double[] sortAndRevert(final double[] array) {
		final int[] indexes = sort(array);

		final double[] result = new double[array.length];
		for (int i = 0; i < result.length; ++i) {
			result[indexes[result.length - 1 - i]] = array[indexes[i]];
		}

		return result;
	}