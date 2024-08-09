public double error(final ProgramGene<Double> program) {
		final Samples samples = _samples.get();
		assert samples != null;

		final double[] calculated = Stream.of(samples.arguments())
			.mapToDouble(args -> eval(program, args))
			.toArray();

		final double err = _error.apply(samples.results(), calculated);
		final double cpx = _complexity.apply(program, err);

		return err + cpx;
	}