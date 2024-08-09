public double error(final Tree<Op<Double>, ?> program) {
		final Samples samples = _samples.get();
		assert samples != null;

		final double[] calculated = Stream.of(samples.arguments())
			.mapToDouble(args -> eval(program, args))
			.toArray();

		return _error.apply(program, calculated, samples.results());
	}