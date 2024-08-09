public static Op<Double> toMathOp(final String string) {
		requireNonNull(string);

		final Op<Double> result;
		final Optional<Const<Double>> cop = toConst(string);
		if (cop.isPresent()) {
			result = cop.orElseThrow(AssertionError::new);
		} else {
			final Optional<Op<Double>> mop = toOp(string);
			result = mop.isPresent()
				? mop.orElseThrow(AssertionError::new)
				: Var.parse(string);
		}

		return result;
	}