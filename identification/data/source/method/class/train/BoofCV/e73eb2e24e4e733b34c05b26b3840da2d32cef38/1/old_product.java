public int get(int x, int y) {
		if (!isInBounds(x, y))
			throw new ImageAccessException("Requested pixel is out of bounds");

		return data[getIndex(x, y)];
	}