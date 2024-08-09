public boolean contains(final char c) {
		return Arrays.binarySearch(proxy._array, c) >= 0;
	}