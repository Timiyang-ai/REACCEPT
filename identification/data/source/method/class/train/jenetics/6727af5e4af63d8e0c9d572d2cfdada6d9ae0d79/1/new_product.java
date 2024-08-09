public static MathExpr parse(final String expression) {
		final Tree<? extends Op<Double>, ?> tree = parseTree(expression);
		Program.check(tree);
		return new MathExpr(tree, true);
	}