public double error(final Tree<Op<Double>, ?> program) {
		final List<Sample> list = _samples.get();
		final Samples samples = list instanceof Samples
			? (Samples)list
			: new Samples(list.toArray(new Sample[0]));

		final double[] calculated = Stream.of(samples.arguments())
			.mapToDouble(args -> eval(program, args))
			.toArray();

		return _error.apply(program, calculated, samples.results());
	}