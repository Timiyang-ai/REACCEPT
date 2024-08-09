@Override
	public int get(int x, int y) {
		if (!isInBounds(x, y))
			throw new ImageAccessException("Requested pixel is out of bounds");

		if( signed )
			return data[getIndex(x, y)];
		else
			return data[getIndex(x, y)] & 0xFFFF;
	}