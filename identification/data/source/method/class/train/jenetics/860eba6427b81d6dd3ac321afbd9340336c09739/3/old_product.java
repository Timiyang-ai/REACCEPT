@Override
	public Function<N, Float64> pdf() {
		return new PDF<N>(_x1, _y1, _x2, _y2);
	}