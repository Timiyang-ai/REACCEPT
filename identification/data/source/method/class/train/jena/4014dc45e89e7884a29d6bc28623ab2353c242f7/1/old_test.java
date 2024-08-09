	@Test
	public void notifyAddTest() {
		Object principal = securityEvaluator.getPrincipal();
		final Set<Action> ADD = SecurityEvaluator.Util.asSet(new Action[] {
				Action.Create, Action.Read });
		g.add(tripleArray[0]);
		if (securityEvaluator.evaluateAny(principal, ADD, sg.getModelNode())) {
			Assert.assertTrue("Should recorded add", listener.isAdd());
		} else {
			Assert.assertFalse("Should not have recorded add", listener.isAdd());
		}
		g.delete(Triple.ANY);
		listener.reset();
	}