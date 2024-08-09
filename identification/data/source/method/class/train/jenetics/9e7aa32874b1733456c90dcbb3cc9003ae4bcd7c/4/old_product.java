public boolean contains(final char c) {
		return Arrays.binarySearch(proxy._characters, c) >= 0;
	}