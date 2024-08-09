@Test
	public void testGetStatement() {
		try {
			getSecuredReifiedStatement().getStatement();
			if (!securityEvaluator.evaluate(Action.Read)) {
				Assert.fail("Should have thrown ReadDeniedException Exception");
			}
		} catch (final ReadDeniedException e) {
			if (securityEvaluator.evaluate(Action.Read)) {
				Assert.fail(String
						.format("Should not have thrown ReadDeniedException Exception: %s - %s",
								e, e.getTriple()));
			}
		}
	}