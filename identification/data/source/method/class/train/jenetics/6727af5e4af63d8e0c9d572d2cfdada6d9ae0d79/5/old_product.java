public static MathExpr parse(final String expression) {
		return new MathExpr(Parser.parse(expression));
	}