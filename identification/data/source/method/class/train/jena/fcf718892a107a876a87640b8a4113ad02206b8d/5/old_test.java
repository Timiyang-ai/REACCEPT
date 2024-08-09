	@Test
	public void sameValueAs() {
		try {
			getSecuredLiteral().sameValueAs(
					ResourceFactory.createPlainLiteral("Junk"));
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