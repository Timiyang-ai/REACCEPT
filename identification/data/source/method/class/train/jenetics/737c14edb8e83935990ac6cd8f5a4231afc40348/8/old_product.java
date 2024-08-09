public double error(final Tree<? extends Op<Double>, ?> expr) {
		return Arrays.stream(_data)
			.mapToDouble(sample -> _error
				.applyAsDouble(eval(expr, sample), sample[sample.length - 1]))
			.sum();
	}