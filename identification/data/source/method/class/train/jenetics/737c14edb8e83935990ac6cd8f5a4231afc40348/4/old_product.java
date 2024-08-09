public double error(final Tree<Op<T>, ?> program) {
		@SuppressWarnings("unchecked")
		final T[] calculated = (T[])Stream.of(_samples.arguments())
			.map(args -> Program.eval(program, args))
			.toArray();

		return _error.apply(program, calculated, _samples.results());
	}