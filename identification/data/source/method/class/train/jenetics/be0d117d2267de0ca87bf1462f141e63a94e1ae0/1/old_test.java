	@Test
	public void format() {
		final String expr = "(((5.0 - 6.0*x) - (3.0 + 4.0)) + sin(x)^34.0) + (1.0 + sin(x*5.0)/4.0)/6.0";
		Assert.assertEquals(MathExpr.format(MathExpr.parse(expr).toTree()), expr);
	}