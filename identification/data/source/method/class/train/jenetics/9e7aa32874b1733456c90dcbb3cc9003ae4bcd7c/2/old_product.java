public boolean contains(final char c) {
		return Arrays.binarySearch(_characters, c) >= 0;
	}