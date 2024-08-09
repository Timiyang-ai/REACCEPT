@Override
	public Function<N, Float64> cdf() {
		return new CDF<>(_domain);
	}