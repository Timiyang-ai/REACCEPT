@Override
	public Function<N, Float64> getPDF() {
		return new PDF<>(_domain);
	}