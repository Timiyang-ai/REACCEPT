public static double eval(final String expression, final double... args) {
		return parse(expression).apply(args);
	}