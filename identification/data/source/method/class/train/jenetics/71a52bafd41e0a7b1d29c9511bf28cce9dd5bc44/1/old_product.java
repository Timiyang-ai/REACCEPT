@Override
	public Function<N, Float64> cdf() {
		return new CDF<N>(_x1, _y1, _x2, _y2);
	}