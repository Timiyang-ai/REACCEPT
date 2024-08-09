@Override
	public Function<N, Float64> getCDF() {
		return new CDF<>(_domain);
	}