public double eval(final double... args) {
		return apply(DoubleStream.of(args).boxed().toArray(Double[]::new));
	}