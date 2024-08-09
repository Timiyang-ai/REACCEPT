@Override
	public Function<N, Float64> getPDF() {
		return new PDF<>(_x1, _y1, _x2, _y2);
	}