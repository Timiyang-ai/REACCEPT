public double eval(final double... args) {
		final double val = apply(
			DoubleStream.of(args)
				.boxed()
				.toArray(Double[]::new)
		);
		return val == -0.0 ? 0.0 : val;
	}