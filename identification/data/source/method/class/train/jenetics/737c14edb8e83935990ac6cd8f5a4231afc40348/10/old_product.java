public double error(final Tree<Op<Double>, ?> program) {
		final double[] calculated = Stream.of(_samples.arguments())
			.mapToDouble(args -> eval(program, args))
			.toArray();

		return _error.apply(program, calculated, _samples.results());
	}