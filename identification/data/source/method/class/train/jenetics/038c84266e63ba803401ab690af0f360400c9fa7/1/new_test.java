	@Test
	public void toMathOp() {
		final TreeNode<Op<Double>> tree = TreeNode.parse(
			"add(mul(x[0],y[1]),sub(y[1],x[0]))",
			MathOp::toMathOp
		);

		assertEquals(Program.eval(tree, 10.0, 5.0).doubleValue(), 45.0);
	}