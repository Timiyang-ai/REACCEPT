	@Test(dataProvider = "functionData")
	public void eval(final String expression, final F3 f, final double[] x) {
		Assert.assertEquals(
			MathExpr.eval(expression, x),
			f.apply(x[0], x[1], x[2])
		);

		Assert.assertEquals(
			MathExpr.eval(MathExpr.parse(expression).toString(), x),
			f.apply(x[0], x[1], x[2])
		);
	}