public static double[] revert(final double[] array) {
        for (int i = 0, j = array.length - 1; i < j; ++i, --j) {
            swap(array, i, j);
        }

        return array;
    }