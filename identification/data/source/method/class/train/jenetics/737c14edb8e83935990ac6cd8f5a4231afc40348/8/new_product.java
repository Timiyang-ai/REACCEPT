public double error(
		final ProgramGene<Double> program,
		final Error error,
		final Complexity complexity
	) {
		final double[] calculated = Arrays.stream(_samples.arguments)
			.mapToDouble(args -> eval(program, args))
			.toArray();

		final double err = error.apply(_samples.results, calculated);
		final double cpx = complexity.apply(program, err);

		return err + cpx;
	}