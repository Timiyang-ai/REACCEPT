	@Test
	public void parse() {
		Assert.assertEquals(
			MathExpr.eval("3*4"),
			12.0
		);
		Assert.assertEquals(
			MathExpr.eval("3*4*x", 2),
			24.0
		);
		Assert.assertEquals(
			MathExpr.eval("3*4*x + y", 2, 4),
			28.0
		);
	}