@Test
	public void testCreateEvaluation() {
		// SUCCESS
		EvaluationData e = new EvaluationData();
		e.course = "Winzor101";
		e.name = "Basic Herping Derping";
		e.startTime = new Date();
		e.endTime = new Date();
		
		try {
			evaluationsDb.createEvaluation(e);
		} catch (EntityAlreadyExistsException ex) {
			fail();
		}
		
		// FAIL : duplicate
		try {
			evaluationsDb.createEvaluation(e);
			fail();
		} catch (EntityAlreadyExistsException ex) {
			
		}
		
		// FAIL : invalid params
		e.startTime = null;
		try {
			evaluationsDb.createEvaluation(e);
			fail();
		} catch (AssertionError a) {
			
		} catch (EntityAlreadyExistsException ex) {
			fail();
		}
	}