@Override
	public Function<N, Float64> pdf() {
		return new PDF<N>(_domain);
	}