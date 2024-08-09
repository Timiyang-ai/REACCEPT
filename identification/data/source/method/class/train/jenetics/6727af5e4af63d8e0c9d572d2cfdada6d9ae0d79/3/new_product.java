public static double eval(final String expression, final double... args) {
		return parse(expression).eval(args);
	}