public boolean contains(final char c) {
		return Arrays.binarySearch(proxy.array.values, c) >= 0;
	}