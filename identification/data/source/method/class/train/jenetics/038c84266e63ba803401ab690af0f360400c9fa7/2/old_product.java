private static Optional<Op<Double>> toMathOp(final String string) {
		return Stream.of(values())
			.filter(op -> Objects.equals(op._name, string))
			.map(op -> (Op<Double>)op)
			.findFirst();
	}