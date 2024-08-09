public boolean contains(final char c) {
		return Arrays.binarySearch(proxy.array, c) >= 0;
	}