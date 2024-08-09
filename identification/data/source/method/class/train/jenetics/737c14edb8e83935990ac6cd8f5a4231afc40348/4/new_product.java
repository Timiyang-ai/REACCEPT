public double error(final Tree<Op<T>, ?> program) {
		@SuppressWarnings("unchecked")
		final T[] calculated = Stream.of(_samples.arguments())
			.map(args -> Program.eval(program, args))
			.toArray(size -> (T[])Array.newInstance(_samples.type(), size));

		return _error.apply(program, calculated, _samples.results());
	}