public static MathExpr parse(final String expression) {
		final TreeNode<Op<Double>> tree = parseTree(expression);
		Program.check(tree);
		return new MathExpr(tree);
	}