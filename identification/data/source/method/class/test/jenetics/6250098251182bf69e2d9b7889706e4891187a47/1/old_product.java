@Override
	public Function<N, Float64> pdf() {
		return new PDF<>(_domain);
	}