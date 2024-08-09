	@Test
	public void vars() {
		final MathExpr expr = MathExpr.parse("a*b*c + a/4 - sin(b*a) + d*d*b");
		Assert.assertEquals(expr.vars().map(Var::name), ISeq.of("a", "b", "c", "d"));
	}