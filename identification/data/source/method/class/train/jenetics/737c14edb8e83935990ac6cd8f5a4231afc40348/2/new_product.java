public double error(final ProgramGene<Double> program) {
		final Samples samples = _samples.get();
		assert samples != null;

		final double[] calculated = Stream.of(samples.arguments())
			.mapToDouble(args -> eval(program, args))
			.toArray();

		Error e = null;
		e.apply(program, calculated, samples.results());

		final double err = _error.apply(calculated, samples.results());
		return 0;//_complexity.apply(program, err);
	}